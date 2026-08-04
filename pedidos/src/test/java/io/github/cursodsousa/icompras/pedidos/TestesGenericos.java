package io.github.cursodsousa.icompras.pedidos;

import io.github.cursodsousa.icompras.pedidos.model.Pedido;
import io.github.cursodsousa.icompras.pedidos.repository.PedidoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class TestesGenericos {
    @Autowired
    PedidoRepository pedidoRepository;
    @Test
    public void verPedidos(){

        Optional<Pedido> pedido = pedidoRepository.findById(1L);
        if(pedido.isPresent()){

            System.out.println(pedido.get().getStatusPedido());
        }
    }
}
