package io.github.cursodsousa.icompras.clientes.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "clientes")
@Data
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    @Column(name = "nome", nullable = false, length = 150)
    private  String nome;
    @Column(name ="cpf", nullable = false, length = 11)
    private  String cpf;
    @Column(name = "logradouro", nullable = true, length =  100)
    private String logradouro;
    @Column(name = "numero", nullable = true, length = 10)
    private  String numero;
    @Column(name = "bairro", nullable = true, length = 100)
    private String bairro;
    @Column(name = "email", nullable = true, length = 150)
    private String email;
    @Column(name = "telefone", nullable = true, length = 150)
    private String telefone;
}
