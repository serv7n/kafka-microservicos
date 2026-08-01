package io.github.cursodsousa.icompras.pedidos.mappers;


import io.github.cursodsousa.icompras.pedidos.DTOs.DadosPagamentoDTO;
import io.github.cursodsousa.icompras.pedidos.DTOs.NovoPedidoDTO;
import io.github.cursodsousa.icompras.pedidos.Enums.StatusPedido;
import io.github.cursodsousa.icompras.pedidos.model.DadosPagamento;
import io.github.cursodsousa.icompras.pedidos.model.ItemPedido;
import io.github.cursodsousa.icompras.pedidos.model.Pedido;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
@Component
public class PedidoMapper {
    public   Pedido toEntity(NovoPedidoDTO pedidoDTO) {
        Pedido pedido = new Pedido();
        pedido.setCodigoCliente(pedidoDTO.codigoCliente());
        pedido.setDataPedido(LocalDateTime.now());
        pedido.setStatusPedido(StatusPedido.REALIZADO);
        pedido.setDadosPagamento(dadosPagamentoToEntity(pedidoDTO.dadosPagamentoDTO()));
        pedido.setItens(getItemPedidoEntity(pedidoDTO));
        pedido.setTotal(somaTotal(pedidoDTO));
        pedido.getItens().forEach(item -> {item.setPedido(pedido);});
        return pedido;
    }

    private  BigDecimal somaTotal(NovoPedidoDTO pedidoDTO) {
        return  pedidoDTO.itens().stream().map(i ->  i.valorUnitario().multiply(BigDecimal.valueOf(i.quantidade()))).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private  List<ItemPedido> getItemPedidoEntity(NovoPedidoDTO pedidoDTO) {
        return pedidoDTO
                .itens()
                .stream()
                .map(ItemPedidoMapper::toEntity)
                .toList();
    }

    public  DadosPagamento dadosPagamentoToEntity(DadosPagamentoDTO dadosPagamentoDTO) {
        DadosPagamento dadosPagamento = new DadosPagamento();
        dadosPagamento.setDados(dadosPagamentoDTO.dados());
        dadosPagamento.setTipoPagamento(dadosPagamentoDTO.tipoPagamento());
        return dadosPagamento;
    }
}
