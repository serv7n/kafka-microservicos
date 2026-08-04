package io.github.cursodsousa.icompras.pedidos.controller;


import io.github.cursodsousa.icompras.pedidos.DTOs.AdicionarNovoDadosPagamentosDTO;
import io.github.cursodsousa.icompras.pedidos.DTOs.DadosPagamentoDTO;
import io.github.cursodsousa.icompras.pedidos.DTOs.ErroDTO;
import io.github.cursodsousa.icompras.pedidos.DTOs.NovoPedidoDTO;
import io.github.cursodsousa.icompras.pedidos.exception.ItemNaoEncontradoException;
import io.github.cursodsousa.icompras.pedidos.exception.ValidationException;
import io.github.cursodsousa.icompras.pedidos.model.Pedido;
import io.github.cursodsousa.icompras.pedidos.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("pedidos")
public class PedidoController {
    private final PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<Object> criar(@RequestBody NovoPedidoDTO pedidoDTO) {
        try {
            Pedido novoPedido = pedidoService.criarPedido(pedidoDTO);
            return ResponseEntity.ok(novoPedido.getCodigo());
        }catch (ValidationException e){
            ErroDTO erroDTO = new ErroDTO("Erro validacao",
                    e.getMessage(),
                    e.getField());
            return ResponseEntity.badRequest().body(erroDTO);
        }

    }
    @PostMapping("pagamentos")
    public ResponseEntity<Object> pagamentos(@RequestBody AdicionarNovoDadosPagamentosDTO dadosPagamentosDTO) {
        try {

        pedidoService.adicionarNovoPagamento(
                dadosPagamentosDTO.codigoPedido(),
                dadosPagamentosDTO.dados(),
                dadosPagamentosDTO.tipoPagamento());

        return ResponseEntity.noContent().build();
        }catch (ItemNaoEncontradoException e){
            ErroDTO erro = new ErroDTO("Erro campo pedido", e.getMessage(),"codigoPedido");
            return ResponseEntity.badRequest().body(erro);
        }
    }
}
