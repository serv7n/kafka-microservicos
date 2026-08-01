package io.github.cursodsousa.icompras.pedidos.mappers;

import io.github.cursodsousa.icompras.pedidos.DTOs.ItemPedidoDTO;
import io.github.cursodsousa.icompras.pedidos.model.ItemPedido;
import io.github.cursodsousa.icompras.pedidos.model.Pedido;
import org.springframework.stereotype.Component;

@Component
public class ItemPedidoMapper {
    public static ItemPedido toEntity(ItemPedidoDTO itemPedidoDTO) {
        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setCodigoProduto(itemPedidoDTO.codigoProduto());
        itemPedido.setQuantidade(itemPedidoDTO.quantidade());
        return  itemPedido;
    }

}
