package io.github.cursodsousa.icompras.pedidos.mapper;

import io.github.cursodsousa.icompras.pedidos.Enum.StatusEnum;
import io.github.cursodsousa.icompras.pedidos.dto.ItemPedidoDTO;
import io.github.cursodsousa.icompras.pedidos.dto.NovoPedidoDTO;
import io.github.cursodsousa.icompras.pedidos.model.ItemPedido;
import io.github.cursodsousa.icompras.pedidos.model.Pedido;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PedidoMapper {
    ItemPedidoMapper ITEN_PEDIDO_MAPPER = Mappers.getMapper(ItemPedidoMapper.class);

    @Mapping(source = "itens", target = "itens", qualifiedByName = "mapItens")
    Pedido toPedido(NovoPedidoDTO dto);
    @Named("mapItens")
    default List<ItemPedido> pedidoMapper(List<ItemPedidoDTO> dtos){
        return dtos.stream().map(ITEN_PEDIDO_MAPPER::toPedido).toList();
    }

    @AfterMapping
    default void affterMapping(@MappingTarget Pedido pedido){
        pedido.setStatus(StatusEnum.REALIZADO);
        pedido.setDataPedido(LocalDate.now());
        var total = getBigDecimal(pedido);
        pedido.setTotal(total);
    }

    private static BigDecimal getBigDecimal(Pedido pedido) {
        var total = pedido.getItens().stream().map(item ->{
           return item.getValorUnitario().multiply( BigDecimal.valueOf(item.getQuantidade()));
        }).reduce(BigDecimal.ZERO, BigDecimal::add).abs();
        return total;
    }


}
