package io.github.cursodsousa.icompras.pedidos.mappers;

import io.github.cursodsousa.icompras.pedidos.DTOs.DadosPagamentoDTO;
import io.github.cursodsousa.icompras.pedidos.DTOs.ItemPedidoDTO;
import io.github.cursodsousa.icompras.pedidos.DTOs.NovoPedidoDTO;
import io.github.cursodsousa.icompras.pedidos.Enums.StatusPedido;
import io.github.cursodsousa.icompras.pedidos.Enums.TipoPagamento;
import io.github.cursodsousa.icompras.pedidos.model.ItemPedido;
import io.github.cursodsousa.icompras.pedidos.model.Pedido;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MapperTest {

    @Test
    void deveConverterItemPedidoDTOParaEntity() {

        ItemPedidoDTO dto = new ItemPedidoDTO(
                1L,
                3L,
                BigDecimal.valueOf(25)
        );

        ItemPedido entity = ItemPedidoMapper.toEntity(dto);

        assertNotNull(entity);
        assertEquals(1L, entity.getCodigoProduto());
        assertEquals(3, entity.getQuantidade());
    }

    @Test
    void deveConverterNovoPedidoDTOParaEntity() {

        DadosPagamentoDTO pagamentoDTO =
                new DadosPagamentoDTO("Chave PIX",
                        TipoPagamento.PIX

                );

        ItemPedidoDTO item1 = new ItemPedidoDTO(
                1L,
                2L,
                BigDecimal.valueOf(50)
        );

        ItemPedidoDTO item2 = new ItemPedidoDTO(
                2L,
                1L,
                BigDecimal.valueOf(30)
        );

        NovoPedidoDTO pedidoDTO = new NovoPedidoDTO(
                100L,
                pagamentoDTO,
                List.of(item1, item2)
        );

        Pedido pedido = PedidoMapper.toEntity(pedidoDTO);

        assertNotNull(pedido);

        assertEquals(100L, pedido.getCodigoCliente());

        assertEquals(StatusPedido.REALIZADO, pedido.getStatusPedido());

        assertNotNull(pedido.getDataPedido());

        assertEquals(
                BigDecimal.valueOf(130),
                pedido.getTotal()
        );

        assertEquals(2, pedido.getItens().size());

        assertEquals(
                TipoPagamento.PIX,
                pedido.getDadosPagamento().getTipoPagamento()
        );

        assertEquals(
                "Chave PIX",
                pedido.getDadosPagamento().getDados()
        );

        pedido.getItens().forEach(item ->
                assertSame(pedido, item.getPedido())
        );
    }
}