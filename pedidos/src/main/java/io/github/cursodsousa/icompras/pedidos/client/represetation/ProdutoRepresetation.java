package io.github.cursodsousa.icompras.pedidos.client.represetation;

import java.math.BigDecimal;

public record ProdutoRepresetation(Long codigo,
                                   String nome,
                                   BigDecimal valorUnitario) {
}
