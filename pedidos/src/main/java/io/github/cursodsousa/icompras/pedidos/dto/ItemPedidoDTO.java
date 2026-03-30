package io.github.cursodsousa.icompras.pedidos.dto;

import java.math.BigDecimal;

public record ItemPedidoDTO(Long codigoProduto, Integer quantidade, BigDecimal valorUnitario) {
}
