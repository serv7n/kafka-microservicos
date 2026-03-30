package io.github.cursodsousa.icompras.pedidos.Service;

import io.github.cursodsousa.icompras.pedidos.Repository.ItemPedidoRepository;
import io.github.cursodsousa.icompras.pedidos.Repository.PedidoRepository;
import io.github.cursodsousa.icompras.pedidos.model.Pedido;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class PedidoService {
    PedidoRepository pedidoRepository;
    ItemPedidoRepository itemPedidoRepository;
    @Transactional
    public Pedido criarPedido(Pedido pedido){
        pedidoRepository.save(pedido);
        itemPedidoRepository.saveAll(pedido.getItens());
        return pedido;
    }
}
