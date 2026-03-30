package io.github.cursodsousa.icompras.pedidos.mapper;

import io.github.cursodsousa.icompras.pedidos.dto.ItemPedidoDTO;
import io.github.cursodsousa.icompras.pedidos.model.ItemPedido;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemPedidoMapper {
    ItemPedidoDTO toDTO(ItemPedido itemPedido);
    ItemPedido toPedido(ItemPedidoDTO itemPedidoDTO);
}
