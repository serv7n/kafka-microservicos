package io.github.cursodsousa.icompras.produtos.controller;

import io.github.cursodsousa.icompras.produtos.model.Produto;
import io.github.cursodsousa.icompras.produtos.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(name = "produtos")
public class ProdutoController {

    private final ProdutoService produtoService;


    @PostMapping
    public ResponseEntity<Produto> novoProduto(@RequestBody Produto produto){
        produtoService.salvar(produto);
        return ResponseEntity.ok(produto);
    }
    @GetMapping("{codigo}")
    public ResponseEntity<Produto> buscarProdutoPorCodigo(@PathVariable Long codigo){
        return  produtoService.findByCodigo(codigo)
                .map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }


}
