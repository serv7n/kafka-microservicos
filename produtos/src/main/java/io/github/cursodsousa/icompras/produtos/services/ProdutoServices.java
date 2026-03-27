package io.github.cursodsousa.icompras.produtos.services;

import io.github.cursodsousa.icompras.produtos.model.Produto;
import io.github.cursodsousa.icompras.produtos.repository.ProdutosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProdutoServices {
    private final ProdutosRepository repository;
    public Produto salvar(Produto produto){
        return repository.save(produto);
    }
    public Optional<Produto> findProduto(Long codigo){
        return repository.findById(codigo);
    }

}
