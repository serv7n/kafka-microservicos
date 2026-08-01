package io.github.cursodsousa.icompras.produtos.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "produtos")
@Data
public class Produto {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long codigo;
    private String nome;

    @Column(name = "valor_unitario")
    private BigDecimal valorUnitario;

}


