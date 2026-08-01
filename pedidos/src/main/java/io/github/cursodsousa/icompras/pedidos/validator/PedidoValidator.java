package io.github.cursodsousa.icompras.pedidos.validator;

import feign.FeignException;
import io.github.cursodsousa.icompras.pedidos.client.ClientesClient;
import io.github.cursodsousa.icompras.pedidos.client.ProdutosClient;
import io.github.cursodsousa.icompras.pedidos.client.represetation.ClienteRepresetation;
import io.github.cursodsousa.icompras.pedidos.client.represetation.ProdutoRepresetation;
import io.github.cursodsousa.icompras.pedidos.model.ItemPedido;
import io.github.cursodsousa.icompras.pedidos.model.Pedido;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PedidoValidator {
    private final ProdutosClient  produtosClient;
    private final ClientesClient clientesClient;

    public void validar(Pedido pedido) {
        Long codigoCliente = pedido.getCodigo();
        validarPedido(pedido);
        pedido.getItens().forEach(this::validarItemPedido);
    }
    private void validarPedido(Pedido pedido) {
        try {
            ResponseEntity<ClienteRepresetation> response = clientesClient.obterCliente(pedido.getCodigoCliente());
            ClienteRepresetation clienteRepresetation = response.getBody();
            log.info("Validando pedido com sucesso {}", clienteRepresetation);
        }catch (FeignException.NotFound e){
            log.error("cliente nao encontrado"+e.getMessage());
        }
    }
    private void validarItemPedido(ItemPedido itemPedido) {
        try {
            ResponseEntity<ProdutoRepresetation> response = produtosClient.obterProdutos(itemPedido.getCodigoProduto());
            ProdutoRepresetation produtoRepresetation = response.getBody();
            log.info("Validado com sucesso {}", produtoRepresetation);
        }catch (FeignException.NotFound e){
            log.error("Produto nao encontrado"+ e.getMessage());
        }
    }

}
