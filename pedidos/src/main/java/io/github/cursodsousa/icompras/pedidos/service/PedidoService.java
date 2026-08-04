package io.github.cursodsousa.icompras.pedidos.service;


import io.github.cursodsousa.icompras.pedidos.DTOs.NovoPedidoDTO;
import io.github.cursodsousa.icompras.pedidos.Enums.StatusPedido;
import io.github.cursodsousa.icompras.pedidos.Enums.TipoPagamento;
import io.github.cursodsousa.icompras.pedidos.client.ServicoBancarioClient;
import io.github.cursodsousa.icompras.pedidos.exception.ItemNaoEncontradoException;
import io.github.cursodsousa.icompras.pedidos.mappers.PedidoMapper;
import io.github.cursodsousa.icompras.pedidos.model.DadosPagamento;
import io.github.cursodsousa.icompras.pedidos.model.Pedido;
import io.github.cursodsousa.icompras.pedidos.repository.ItemPedidoRepository;
import io.github.cursodsousa.icompras.pedidos.repository.PedidoRepository;
import io.github.cursodsousa.icompras.pedidos.validator.PedidoValidator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PedidoService {
    private final PedidoRepository pedidoRepository;
    private final ItemPedidoRepository itemPedidoRepository;
    private final PedidoValidator pedidoValidator;
    private final ServicoBancarioClient  servicoBancarioClient;
    private final PedidoMapper pedidoMapper;
    @Transactional
    public Pedido criarPedido(NovoPedidoDTO novoPedidoDTO) {
        Pedido pedido = pedidoMapper.toEntity(novoPedidoDTO);

        pedidoRepository.save(pedido);
        itemPedidoRepository.saveAll(pedido.getItens());
        pedidoValidator.validar(pedido);
        enviaSolicitacaoDePagamento(pedido);
        return pedido;
    }

    private void enviaSolicitacaoDePagamento(Pedido pedido) {
        var chave = servicoBancarioClient.solicitarPagamento(pedido);
        pedido.setChavePagamento(chave);
    }
    @Transactional
    public void atualizarStatusPagamento(Long codigoPedido, String chavePagamento, Boolean sucesso, String observacoes) {
        Optional<Pedido> pedidoOptional = pedidoRepository.findById(codigoPedido);
        if(pedidoOptional.isEmpty()) {
            log.error("pedido do codigo: " + codigoPedido + " nao encontrado");
            return;
        }

        Pedido pedido = pedidoOptional.get();
        if (sucesso){
            pedido.setStatusPedido(StatusPedido.PAGO);

        }
        else {
            pedido.setStatusPedido(StatusPedido.ERRO_PAGAMENTO);
            pedido.setObservacoes(observacoes);
        }
        pedidoRepository.save(pedido);
    }
    @Transactional
    public void adicionarNovoPagamento(Long codigoPedido,
                                       String dados,
                                       TipoPagamento tipoPagamento) {
        var pedidoEncontrado = pedidoRepository.findById(codigoPedido);
        if (pedidoEncontrado.isEmpty()) {
            throw new ItemNaoEncontradoException("pedido nao encontrado");
        }
        var pedido = pedidoEncontrado.get();
        DadosPagamento dadosPagamento =  new DadosPagamento();
        dadosPagamento.setDados(dados);
        dadosPagamento.setTipoPagamento(tipoPagamento);
        pedido.setDadosPagamento(dadosPagamento);
        pedido.setObservacoes("novo pagamento realizado espere o processamento");
        pedido.setStatusPedido(StatusPedido.REALIZADO);
        pedidoRepository.save(pedido);
    }
}
