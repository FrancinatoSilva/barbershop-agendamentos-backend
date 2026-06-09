package br.com.natodev.gerenciadordeagendamentosbarbershop.service;

import br.com.natodev.gerenciadordeagendamentosbarbershop.domain.Cliente;
import br.com.natodev.gerenciadordeagendamentosbarbershop.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public Cliente salvarCliente(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public List<Cliente> listarClientes(){
        return clienteRepository.findAll();
    }

    public Cliente buscarClientePorId(Long id){

        return clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente com ID: " + id +
                        " não foi encontrado."));
    }
}
