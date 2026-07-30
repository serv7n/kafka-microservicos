package io.github.cursodsousa.icompras.pedidos.model;

import io.github.cursodsousa.icompras.pedidos.Enums.StatusPedido;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pedido")
@Setter
@Getter
@NoArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @Column(name = "codigo_cliente")
    private Long codigoCliente;

    @Column(name = "data_pedido")
    private LocalDateTime dataPedido;

    @Column(name = "chave_pagamento")
    private String chavePagamento;
    @Column(name = "observacoes")
    private String observacoes;

    @Enumerated(EnumType.STRING)
    private StatusPedido statusPedido;

    private BigDecimal total;

    @Column(name = "codigo_rastreio")
    private String codigo_rastreio;

    @Column(name = "url_nf")
    private String urlNotaFiscal;

    @Transient
    private DadosPagamento dadosPagamento;

    @OneToMany(mappedBy = "codigoPedido")
    private List<ItemPedido> itens;

}
