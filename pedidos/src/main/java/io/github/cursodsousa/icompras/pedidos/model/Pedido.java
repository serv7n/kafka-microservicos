package io.github.cursodsousa.icompras.pedidos.model;




import io.github.cursodsousa.icompras.pedidos.Enum.StatusEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "pedido")
@Data
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    @Column(name = "codigo_cliente", nullable = false)
    private  Long codigoCliente;
    @Column(name = "data_pedido")
    private LocalDate dataPedido;
    @Column(name = "chave_pagamento", nullable = true)
    private String chavePagamento;
    @Column(name = "observacoes", nullable = true)
    private String observacoes;
    @Column(name = "status", nullable = true)
    @Enumerated(EnumType.STRING)
    private StatusEnum status;
    @Column(name = "total", nullable = false, precision = 16, scale = 2)
    private BigDecimal total;
    @Column(name = "codigo_rastreio", nullable = true, length = 255)
    private String codigoRastreio;
    @Column(name="url_nf", nullable = true)
    private  String urlNf;
    @Transient
    private DadosPagamento dadosPagamento;
    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itens;


}
