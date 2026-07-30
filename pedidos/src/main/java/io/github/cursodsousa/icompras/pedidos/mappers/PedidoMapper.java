package io.github.cursodsousa.icompras.pedidos.mappers;


import io.github.cursodsousa.icompras.pedidos.DTOs.DadosPagamentoDTO;
import io.github.cursodsousa.icompras.pedidos.DTOs.NovoPedidoDTO;
import io.github.cursodsousa.icompras.pedidos.Enums.StatusPedido;
import io.github.cursodsousa.icompras.pedidos.model.DadosPagamento;
import io.github.cursodsousa.icompras.pedidos.model.ItemPedido;
import io.github.cursodsousa.icompras.pedidos.model.Pedido;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class PedidoMapper {
    public  static Pedido toEntity(NovoPedidoDTO pedidoDTO) {
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

    private static BigDecimal somaTotal(NovoPedidoDTO pedidoDTO) {
        return  pedidoDTO.itens().stream().map(i ->  i.valorUnitario().multiply(BigDecimal.valueOf(i.quantidade()))).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private static List<ItemPedido> getItemPedidoEntity(NovoPedidoDTO pedidoDTO) {
        return pedidoDTO
                .itens()
                .stream()
                .map(ItemPedidoMapper::toEntity)
                .toList();
    }

    public static DadosPagamento dadosPagamentoToEntity(DadosPagamentoDTO dadosPagamentoDTO) {
        DadosPagamento dadosPagamento = new DadosPagamento();
        dadosPagamento.setDados(dadosPagamentoDTO.dados());
        dadosPagamento.setTipoPagamento(dadosPagamentoDTO.tipoPagamento());
        return dadosPagamento;
    }
}
