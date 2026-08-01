package io.github.cursodsousa.icompras.pedidos.client;


import io.github.cursodsousa.icompras.pedidos.client.represetation.ClienteRepresetation;
import jakarta.websocket.server.PathParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "Cliente", url = "${icompras.pedidos.clients.clients.url}")
public interface ClientesClient {
    @GetMapping("{codigo}")
    public ResponseEntity<ClienteRepresetation> obterCliente(@PathVariable Long codigo);
}
