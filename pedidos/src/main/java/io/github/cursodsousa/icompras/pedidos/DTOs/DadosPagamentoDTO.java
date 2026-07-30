package io.github.cursodsousa.icompras.pedidos.DTOs;

import io.github.cursodsousa.icompras.pedidos.Enums.TipoPagamento;

public record DadosPagamentoDTO(String dados,
                                TipoPagamento tipoPagamento) {
}
