package io.github.cursodsousa.icompras.pedidos.controller;


import io.github.cursodsousa.icompras.pedidos.DTOs.NovoPedidoDTO;
import io.github.cursodsousa.icompras.pedidos.model.Pedido;
import io.github.cursodsousa.icompras.pedidos.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("pedido")
public class PedidoController {
    private final PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<Object> criar(@RequestBody NovoPedidoDTO pedidoDTO) {
        Pedido novoPedido = pedidoService.criarPedido(pedidoDTO);
        System.out.println("Criando novo pedido"+pedidoDTO);
        return ResponseEntity.ok(novoPedido.getItens().getFirst());

    }

}
