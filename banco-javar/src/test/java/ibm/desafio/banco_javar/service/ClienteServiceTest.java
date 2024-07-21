package ibm.desafio.banco_javar.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ibm.desafio.banco_javar.repository.ClienteRepository;
import ibm.desafio.banco_javar.entity.Cliente;
import ibm.desafio.banco_javar.exception.EntidadeNaoEncontradaException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @Mock
    private ClienteRepository repository;
    @InjectMocks
    private ClienteService service;

    @Test
    @DisplayName("Teste correto se, ao chamar listarClientes() retorna uma lista de 2 clientes")
    void cenarioCorreto1() {
        // given
        List<Cliente> clientes = List.of(
                new Cliente(1L, "João", 999999999L, true, 700.0f, 2000.0f),
                new Cliente(2L, "Maria", 888888888L, true, 800.0f, 3000.0f)
        );

        // when
        when(repository.findAll()).thenReturn(clientes);

        // then
        List<Cliente> resposta = service.listarClientes();
        // assert
        assertEquals(2, resposta.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    @DisplayName("Teste incorreto se, ao chamar listarClientes() retorna uma lista vazia")
    void cenarioIncorreto1() {
        // given
        var clientes = new ArrayList<Cliente>();

        // when
        when(repository.findAll()).thenReturn(clientes);
        // then
        List<Cliente> resposta = service.listarClientes();
        // assert
        assertTrue(resposta.isEmpty());
    }

    @Test
    @DisplayName("Teste correto se, ao chamar buscarClientePorId retorna um cliente")
    void cenarioCorreto2() {
        // given
        Cliente cliente = new Cliente(1L, "Carlos", 777777777L, false, 600.0f, 1000.0f);
        Long idInformado = 1L;
        // when
        when(repository.findById(idInformado)).thenReturn(java.util.Optional.of(cliente));
        // then
        Cliente resposta = service.buscarClientePorId(idInformado);
        // assert
        assertEquals(cliente, resposta);
        verify(repository, times(1)).findById(1L);
        verify(repository, times(0)).findAll();
    }

    @Test
    @DisplayName("Teste incorreto se, ao chamar buscarClientePorId retorna uma exception")
    void cenarioIncorreto2() {
        // when
        when(repository.findById(any())).thenReturn(java.util.Optional.empty());

        // then// assert
        assertThrows(EntidadeNaoEncontradaException.class, () -> service.buscarClientePorId(1L));

        // assert
        verify(repository, times(1)).findById(any());
        verify(repository, times(0)).findAll();
    }

    @Test
    @DisplayName("Dado que, vou salvar no banco e retorna o objeto com o id")
    void cenarioSalvaCorreto() {
        // given
        Cliente clienteParaSalvar = new Cliente(null, "Ana", 666666666L, true, 900.0f, 4000.0f);
        Cliente clienteSalvo = new Cliente(1L, "Ana", 666666666L, true, 900.0f, 4000.0f);
        // when
        when(repository.save(clienteParaSalvar)).thenReturn(clienteSalvo);
        // then
        Cliente clienteResposta = service.criarCliente(clienteParaSalvar);
        // assert
        assertNotNull(clienteResposta.getId());
        assertEquals(clienteParaSalvar.getNome(), clienteResposta.getNome());
        verify(repository, times(1)).save(clienteParaSalvar);
        verify(repository, times(0)).findById(any());
    }

//    @Test
//    @DisplayName("Dado que, passei um id valido, salve o objeto atualizado e retorne o objeto atualizado")
//    void cenarioAtualizaCorreto() {
//        // given
//        Cliente clienteParaAtualizar = new Cliente(null, "Pedro", 555555555L, false, 500.0f, 1500.0f);
//        Cliente clienteAtualizado = new Cliente(1L, "Pedro", 555555555L, false, 500.0f, 1500.0f);
//        Long id = 1L;
//
//        // when
//        when(repository.existsById(id)).thenReturn(Boolean.TRUE);
//        when(repository.save(clienteParaAtualizar)).thenReturn(clienteAtualizado);
//        // then
//        Cliente clienteResposta = service.atualizarCliente(id, clienteParaAtualizar);
//
//        // assert
//        assertEquals(clienteParaAtualizar.getNome(), clienteResposta.getNome());
//        assertEquals(clienteParaAtualizar.getSaldo(), clienteResposta.getSaldo());
//
//        verify(repository, times(1)).existsById(id);
//        verify(repository, times(0)).findById(id);
//        verify(repository, times(0)).findAll();
//        verify(repository, times(1)).save(clienteParaAtualizar);
//    }

    @Test
    @DisplayName("Dado que, tenha um id, ao atualizar, não existe o id no banco")
    void cenarioAtualizaIncorreto() {
        when(repository.existsById(any())).thenReturn(Boolean.FALSE);
        assertThrows(EntidadeNaoEncontradaException.class, () -> service.atualizarCliente(1L, null));
        verify(repository, times(1)).existsById(any());
        verify(repository, times(0)).findById(any());
        verify(repository, times(0)).findAll();
    }
}