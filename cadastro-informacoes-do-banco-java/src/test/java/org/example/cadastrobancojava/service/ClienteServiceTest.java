package org.example.cadastrobancojava.service;

import org.example.cadastrobancojava.dto.ClienteCriacaoDTO;
import org.example.cadastrobancojava.dto.DadosClienteListagemDTO;
import org.example.cadastrobancojava.entity.Cliente;
import org.example.cadastrobancojava.exception.EntidadeNaoEncontradaException;
import org.example.cadastrobancojava.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testarCriacaoCliente_Sucesso() {
        ClienteCriacaoDTO clienteDto = new ClienteCriacaoDTO("Nome", 123456789L, true, 100.0f);
        Cliente cliente = new Cliente(clienteDto);
        when(clienteRepository.save(cliente)).thenReturn(cliente);

        DadosClienteListagemDTO clienteCriado = clienteService.criarCliente(clienteDto);

        assertNotNull(clienteCriado);
        assertEquals(cliente.getId(), clienteCriado.getId());
        verify(clienteRepository, times(1)).save(cliente);
    }

    @Test
    void testarCriacaoCliente_DadosInvalidos() {
        ClienteCriacaoDTO clienteDto = new ClienteCriacaoDTO(null, null, null, -1.0f);

        assertThrows(IllegalArgumentException.class, () -> {
            clienteService.criarCliente(clienteDto);
        });
    }

    @Test
    void testarBuscaClientePorId_Sucesso() {
        Cliente cliente = new Cliente(1L, "Nome", 123456789L, true, 100.0f);
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));

        DadosClienteListagemDTO clienteEncontrado = clienteService.buscarClientePorId(1L);

        assertNotNull(clienteEncontrado);
        assertEquals(cliente.getId(), clienteEncontrado.getId());
        verify(clienteRepository, times(1)).findById(1L);
    }

    @Test
    void testarBuscaClientePorId_NaoEncontrado() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            clienteService.buscarClientePorId(1L);
        });
    }

    @Test
    void testarAtualizacaoCliente_Sucesso() {
        Cliente cliente = new Cliente(1L, "Nome", 123456789L, true, 100.0f);
        when(clienteRepository.existsById(1L)).thenReturn(true);
        when(clienteRepository.save(cliente)).thenReturn(cliente);

        DadosClienteListagemDTO clienteAtualizado = clienteService.atualizarCliente(1L, new ClienteCriacaoDTO(cliente));

        assertNotNull(clienteAtualizado);
        assertEquals(cliente.getId(), clienteAtualizado.getId());
        verify(clienteRepository, times(1)).save(cliente);
    }

    @Test
    void testarAtualizacaoCliente_DadosInvalidos() {
        ClienteCriacaoDTO clienteDto = new ClienteCriacaoDTO(null, null, null, -1.0f);
        when(clienteRepository.existsById(1L)).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> {
            clienteService.atualizarCliente(1L, clienteDto);
        });
    }

    @Test
    void testarDelecaoCliente_Sucesso() {
        when(clienteRepository.existsById(1L)).thenReturn(true);
        doNothing().when(clienteRepository).deleteById(1L);

        clienteService.deletarCliente(1L);

        verify(clienteRepository, times(1)).deleteById(1L);
    }

    @Test
    void testarDelecaoCliente_NaoEncontrado() {
        when(clienteRepository.existsById(1L)).thenReturn(false);

        assertThrows(EntidadeNaoEncontradaException.class, () -> {
            clienteService.deletarCliente(1L);
        });
    }
}