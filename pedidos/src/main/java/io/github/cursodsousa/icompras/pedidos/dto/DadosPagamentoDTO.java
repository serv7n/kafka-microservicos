package io.github.cursodsousa.icompras.pedidos.dto;

import io.github.cursodsousa.icompras.pedidos.Enum.TipoPagamento;

public record DadosPagamentoDTO(String dados, TipoPagamento Pagamento) {
}
