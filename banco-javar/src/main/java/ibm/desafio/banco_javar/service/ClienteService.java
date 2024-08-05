package ibm.desafio.banco_javar.service;

import ibm.desafio.banco_javar.entity.Cliente;
import ibm.desafio.banco_javar.exception.EntidadeNaoEncontradaException;
import ibm.desafio.banco_javar.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    public Cliente buscarClientePorId(Long id) {

        return clienteRepository.findById(id)
                .orElseThrow(() ->
                        new EntidadeNaoEncontradaException("Cliente")
                );
    }

    public Cliente criarCliente(Cliente clienteNovo) {
        return clienteRepository.save(clienteNovo);
    }

    public Cliente atualizarCliente(Long id, Cliente clienteAtualizado) {
        if (!clienteRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Cliente");
        }
        clienteAtualizado.setId(id);
        return clienteRepository.save(clienteAtualizado);
    }

    public void deletarCliente(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Cliente");
        }
        clienteRepository.deleteById(id);
    }

    public  Float  calcularScoreCredito(Float saldo){
        return saldo * 0.1f;
    }

}
