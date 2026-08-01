package io.github.cursodsousa.icompras.clientes.controller;


import io.github.cursodsousa.icompras.clientes.model.Cliente;
import io.github.cursodsousa.icompras.clientes.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( "clientes")
@RequiredArgsConstructor
public class ClienteController {
    private final ClienteService service;
    @PostMapping
    public ResponseEntity<Cliente> save(@RequestBody Cliente cliente) {

        Cliente clienteCreated =  service.save(cliente);

        return  ResponseEntity.ok().body(clienteCreated);
    }
    @GetMapping("{codigo}")
    public ResponseEntity<Cliente> findById(@PathVariable Long codigo){
        return service.findByCodigo(codigo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
