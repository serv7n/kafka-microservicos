package io.github.cursodsousa.icompras.produtos.controller;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;




import io.github.cursodsousa.icompras.produtos.model.Produto;
import io.github.cursodsousa.icompras.produtos.repository.ProdutoRepository;
import io.github.cursodsousa.icompras.produtos.service.ProdutoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Optional;

@SpringBootTest
class ProdutoControllerTest {
    @Mock
    private ProdutoRepository repository;
    @Mock
    private ProdutoService service;

    private ProdutoController produtoController;
    Produto produtoMock = new Produto();
    @BeforeEach
    void setUp(){
        produtoController =  new ProdutoController(service);

        produtoMock.setCodigo(1L);
        produtoMock.setValorUnitario(BigDecimal.valueOf(10));
        produtoMock.setNome("Leandro");
    }
    @Test
    void novoProduto_salvaCorretamenteRetornaResponseEntityOk(){
//  configurando a resposta esperada do metodo salvar do service
        when(service.salvar(any(Produto.class))).thenReturn(produtoMock);
        ResponseEntity<Produto> responseEntity = produtoController.novoProduto(produtoMock);
//  verificação das propriedades da resposta
        assertEquals(ResponseEntity.ok().body(produtoMock), responseEntity);

    }
    @Test
    void buscarProdutoPorCodigo_produtoExistenteRetornaResponseEntityOk(){
//        Configurando a resposta esperada do metodo findByCodigo do produtosService
        when(service.findByCodigo(eq(1L)))
                .thenReturn(Optional.of(produtoMock));

        ResponseEntity<Produto> responseEntity = produtoController.buscarProdutoPorCodigo(1L);
        assertEquals(ResponseEntity.ok().body(produtoMock), responseEntity);
    }

    @Test
    void buscarProdutoPorCodigo_produtoInexistenteRetornaResponseEntityNotFound(){
        when(service.findByCodigo(eq(1L)));
        ResponseEntity<Produto> responseEntity = produtoController.buscarProdutoPorCodigo(1L);
        assertEquals(ResponseEntity.ok().body(null), responseEntity);
    }
}