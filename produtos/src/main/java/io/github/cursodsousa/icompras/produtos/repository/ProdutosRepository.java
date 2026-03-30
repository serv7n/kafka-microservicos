package io.github.cursodsousa.icompras.produtos.repository;

import io.github.cursodsousa.icompras.produtos.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutosRepository  extends JpaRepository<Produto,Long> {
}
