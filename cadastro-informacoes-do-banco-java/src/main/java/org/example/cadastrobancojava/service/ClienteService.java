package org.example.cadastrobancojava.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.cadastrobancojava.dto.ClienteCriacaoDTO;
import org.example.cadastrobancojava.dto.DadosClienteListagemDTO;
import org.example.cadastrobancojava.entity.Cliente;
import org.example.cadastrobancojava.exception.EntidadeNaoEncontradaException;
import org.example.cadastrobancojava.repository.ClienteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public Page<DadosClienteListagemDTO> listarClientes(Pageable pageable) {
        return clienteRepository.findAll(pageable)
                .map(DadosClienteListagemDTO::new);
    }

    public DadosClienteListagemDTO buscarClientePorId(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Cliente"));
        return new DadosClienteListagemDTO(cliente);
    }

    @Transactional
    public DadosClienteListagemDTO criarCliente(ClienteCriacaoDTO clienteDto) {
        validarDadosCliente(clienteDto);
        Cliente cliente = new Cliente(clienteDto);
        Cliente clienteSalvo = clienteRepository.save(cliente);
        return new DadosClienteListagemDTO(clienteSalvo);
    }

    @Transactional
    public DadosClienteListagemDTO atualizarCliente(Long id, ClienteCriacaoDTO clienteAtualizado) {
        if (!clienteRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Cliente");
        }
        validarDadosCliente(clienteAtualizado);
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

    private void validarDadosCliente(ClienteCriacaoDTO clienteDto) {
        if (clienteDto.getNome() == null || clienteDto.getNome().isEmpty()) {
            throw new IllegalArgumentException("Nome do cliente é obrigatório");
        }
        if (clienteDto.getTelefone() == null) {
            throw new IllegalArgumentException("Telefone do cliente é obrigatório");
        }
        if (clienteDto.getCorrentista() == null) {
            throw new IllegalArgumentException("Status de correntista é obrigatório");
        }
        if (clienteDto.getSaldo() < 0) {
            throw new IllegalArgumentException("Saldo do cliente não pode ser negativo");
        }
    }
}