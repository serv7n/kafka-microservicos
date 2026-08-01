package io.github.cursodsousa.icompras.pedidos.client;

import io.github.cursodsousa.icompras.pedidos.client.represetation.ProdutoRepresetation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "produtos", url = "${icompras.pedidos.clients.produtos.url}")

public interface ProdutosClient {
    @GetMapping("{codigo}")
    ResponseEntity<ProdutoRepresetation> obterProdutos(@PathVariable Long codigo);
}
