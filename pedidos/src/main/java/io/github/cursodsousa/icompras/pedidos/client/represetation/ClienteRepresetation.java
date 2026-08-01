package io.github.cursodsousa.icompras.pedidos.client.represetation;

public record ClienteRepresetation(Long codigo,
                                   String nome,
                                   String cpf,
                                   String logradouro,
                                   String numero,
                                   String bairro,
                                   String email,
                                   String telefone) {
}
