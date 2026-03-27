package io.github.cursodsousa.icompras.produtos.controller;

import io.github.cursodsousa.icompras.produtos.model.Produto;
import io.github.cursodsousa.icompras.produtos.services.ProdutoServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("produtos")
public class ProdutoController {
    private  final ProdutoServices services;
    @PostMapping
    public ResponseEntity<Produto> salvar(@RequestBody Produto produto){
        return ResponseEntity.ok(services.salvar(produto));
    }
    @GetMapping("/{codigo}")
    public ResponseEntity<Produto> mostrar(@PathVariable Long codigo){
        return  services.findProduto(codigo).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
