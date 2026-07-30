package io.github.cursodsousa.icompras.produtos.service;

import io.github.cursodsousa.icompras.produtos.model.Produto;
import io.github.cursodsousa.icompras.produtos.repository.ProdutoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ProdutoService {
    private final ProdutoRepository repository;

    public Produto salvar(Produto produto){
        return repository.save(produto);
    }
    public Optional<Produto> findByCodigo(Long codigo){
        return repository.findById(codigo);
    }
}
