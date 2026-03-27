package io.github.cursodsousa.icompras.clientes.Service;

import io.github.cursodsousa.icompras.clientes.Repository.ClienteRepository;
import io.github.cursodsousa.icompras.clientes.model.Cliente;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteService {
    private  final ClienteRepository repository;
    public Cliente salvar(Cliente cliente)
    {
        return repository.save(cliente);
    }
    public Optional<Cliente> findClient(Long codigo)
    {
        return repository.findById(codigo);
    }
}
