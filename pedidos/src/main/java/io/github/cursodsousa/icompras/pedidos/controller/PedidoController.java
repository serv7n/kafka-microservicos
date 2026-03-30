package io.github.cursodsousa.icompras.pedidos.controller;


import io.github.cursodsousa.icompras.pedidos.Service.PedidoService;
import io.github.cursodsousa.icompras.pedidos.dto.NovoPedidoDTO;

import io.github.cursodsousa.icompras.pedidos.mapper.PedidoMapper;
import io.github.cursodsousa.icompras.pedidos.model.Pedido;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("pedidos")
@RequiredArgsConstructor
public class PedidoController {
    private final PedidoMapper mapper;
    private final PedidoService service;
    @PostMapping
    public ResponseEntity criar(@RequestBody NovoPedidoDTO dto){
        Pedido pedido = mapper.toPedido(dto);
        service.criarPedido(pedido);
        return ResponseEntity.ok(pedido);
    }
}
