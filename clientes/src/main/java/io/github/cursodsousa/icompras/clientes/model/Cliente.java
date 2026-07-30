package io.github.cursodsousa.icompras.clientes.model;


import jakarta.persistence.*;
import lombok.Data;

@Table(name = "clientes")
@Entity
@Data
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    private String nome;
    private String cpf;
    private String logradouro;
    private String numero;
    private String bairro;
    private String email;
    private String telefone;
}
