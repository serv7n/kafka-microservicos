package io.github.cursodsousa.icompras.pedidos.DTOs;

import java.math.BigDecimal;

public record ItemPedidoDTO(Long codigoProduto,
                            Long quantidade,
                            BigDecimal valorUnitario) {
}
