package io.github.cursodsousa.icompras.pedidos.validator;

import io.github.cursodsousa.icompras.pedidos.client.ProdutosClient;
import io.github.cursodsousa.icompras.pedidos.client.represetation.ProdutoRepresetation;
import io.github.cursodsousa.icompras.pedidos.model.Pedido;
import org.springframework.stereotype.Component;

@Component
public class PedidoValidator {
    private ProdutoRepresetation produtoRepresetation;
    ProdutosClient  produtosClient;
    public void validar(Pedido pedido) {}
}
