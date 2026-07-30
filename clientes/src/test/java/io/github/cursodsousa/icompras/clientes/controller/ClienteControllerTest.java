package io.github.cursodsousa.icompras.clientes.controller;

import io.github.cursodsousa.icompras.clientes.model.Cliente;
import io.github.cursodsousa.icompras.clientes.repository.ClienteRepository;
import io.github.cursodsousa.icompras.clientes.service.ClienteService;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

@SpringBootTest
public class ClienteControllerTest {

    @Mock
    private ClienteService clienteService;

    ClienteController controller;

    Cliente clienteMock = new Cliente();

    @BeforeEach
    public void setUp(){
        controller = new ClienteController(clienteService);
        clienteMock.setCodigo(1L);
        clienteMock.setNome("Leandro");
        clienteMock.setCpf("1234155125");
        clienteMock.setLogradouro("Sn");
        clienteMock.setNumero("23");
        clienteMock.setBairro("12313");
        clienteMock.setEmail("dada@gmail.com");
        clienteMock.setTelefone("(31)4191-9231");
    }
    @Test
    public void salvarCliente(){
        when(clienteService.save(any())).thenReturn(clienteMock);
        ResponseEntity<Cliente> responseEntity = controller.save(clienteMock);
        System.out.print(responseEntity);
        assertEquals(ResponseEntity.ok().body(clienteMock), responseEntity);
    }
    @Test
    public void buscarClientePorCodigo(){
        when(clienteService.findByCodigo(eq(1L)) ).thenReturn(Optional.of(clienteMock));
        ResponseEntity<Cliente> responseEntity = controller.findById(1L);
        assertEquals(ResponseEntity.ok().body(clienteMock), responseEntity);
    }

    @Test
    public void testErroBuscarClientePorCodigo(){
        when(clienteService.findByCodigo(eq(1L)) ).thenReturn(Optional.of(clienteMock));
        ResponseEntity<Cliente> responseEntity = controller.findById(2L);
        assertNotEquals(ResponseEntity.ok().body(clienteMock), responseEntity);
    }
}
