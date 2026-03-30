package io.github.cursodsousa.icompras.clientes.Service.Controller;

import io.github.cursodsousa.icompras.clientes.Service.ClienteService;
import io.github.cursodsousa.icompras.clientes.model.Cliente;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("clientes")
@RequiredArgsConstructor
public class ClienteController {
    private final ClienteService service;
    @PostMapping
    public ResponseEntity<Cliente> salvar(@RequestBody Cliente cliente){
        return ResponseEntity.ok(service.salvar(cliente));
    }
    @GetMapping("{codigo}")
    public ResponseEntity<Cliente> mostrar(@PathVariable Long codigo){
        return service.findCliente(codigo).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
