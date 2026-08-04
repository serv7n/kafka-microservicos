package io.github.cursodsousa.icompras.pedidos.DTOs;

import io.github.cursodsousa.icompras.pedidos.Enums.TipoPagamento;

public record AdicionarNovoDadosPagamentosDTO(Long codigoPedido,
                                              String dados,
                                              TipoPagamento tipoPagamento) {
}
