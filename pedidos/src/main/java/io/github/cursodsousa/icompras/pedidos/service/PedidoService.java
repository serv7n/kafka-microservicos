package io.github.cursodsousa.icompras.pedidos.service;


import io.github.cursodsousa.icompras.pedidos.DTOs.NovoPedidoDTO;
import io.github.cursodsousa.icompras.pedidos.client.ServicoBancarioClient;
import io.github.cursodsousa.icompras.pedidos.mappers.PedidoMapper;
import io.github.cursodsousa.icompras.pedidos.model.Pedido;
import io.github.cursodsousa.icompras.pedidos.repository.ItemPedidoRepository;
import io.github.cursodsousa.icompras.pedidos.repository.PedidoRepository;
import io.github.cursodsousa.icompras.pedidos.validator.PedidoValidator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
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
}
