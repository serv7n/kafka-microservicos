package io.github.cursodsousa.icompras.pedidos.DTOs;
//{
//        "codigo": "number",5
//        "chavePagamento": "string",6
//        "status": "boolean",7
//        "observacoes": "string"8
//        }
public record RecebimentoCallbackPagamentoDTO(Long codigo,
                                              String chavePagamento,
                                              Boolean status,
                                              String observacoes
        ) {
}
