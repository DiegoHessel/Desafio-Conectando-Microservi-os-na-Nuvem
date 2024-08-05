package org.example.cadastrobancojava.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.cadastrobancojava.dto.ClienteCriacaoDTO;
import org.example.cadastrobancojava.dto.DadosClienteListagemDTO;
import org.example.cadastrobancojava.entity.Cliente;
import org.example.cadastrobancojava.exception.EntidadeNaoEncontradaException;
import org.example.cadastrobancojava.repository.ClienteRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public List<DadosClienteListagemDTO> listarClientes(Pageable pageable) {
        return clienteRepository.findAll().stream()
                .map(DadosClienteListagemDTO::new)
                .collect(Collectors.toList());
    }

    public DadosClienteListagemDTO buscarClientePorId(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Cliente"));
        return new DadosClienteListagemDTO(cliente);
    }

    @Transactional
    public DadosClienteListagemDTO criarCliente(ClienteCriacaoDTO clienteDto) {
        Cliente cliente = new Cliente(clienteDto);
        Cliente clienteSalvo = clienteRepository.save(cliente);
        return new DadosClienteListagemDTO(clienteSalvo);
    }

    @Transactional
    public DadosClienteListagemDTO atualizarCliente(Long id, ClienteCriacaoDTO clienteAtualizado) {
        if (!clienteRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Cliente");
        }
        Cliente cliente = new Cliente(clienteAtualizado);
        cliente.setId(id);
        Cliente clienteSalvo = clienteRepository.save(cliente);
        return new DadosClienteListagemDTO(clienteSalvo);
    }

    public void deletarCliente(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Cliente");
        }
        clienteRepository.deleteById(id);
    }

    public Float calcularScoreCredito(Float saldo) {
        return saldo * 0.1f;
    }
}