package ibm.desafio.banco_javar.controller;

import ibm.desafio.banco_javar.dto.ClienteCriacaoDTO;
import ibm.desafio.banco_javar.dto.ClienteListagemDTO;
import ibm.desafio.banco_javar.dto.ClienteMapper;
import ibm.desafio.banco_javar.entity.Cliente;
import ibm.desafio.banco_javar.feign.FeignClientBanco;
import ibm.desafio.banco_javar.service.ClienteServiceApi;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ClienteController.class)
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteServiceApi clienteService;

    @MockBean
    private FeignClientBanco feignClient;

    @Test
    public void testListarClientes() throws Exception {
        // Given
        List<ClienteListagemDTO> clientes = Collections.singletonList(new ClienteListagemDTO(1L, "João Silva", 1000.0f));
        when(clienteService.listarClientes()).thenReturn(clientes);

        // When
        String responseContent = mockMvc.perform(get("/clientes"))
                // Then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].nome").value("João Silva"))
                .andExpect(jsonPath("$[0].saldo").value(1000.0))
                .andReturn()
                .getResponse()
                .getContentAsString();

        System.out.println("Response Content: " + responseContent);

        verify(clienteService, times(1)).listarClientes();
    }
    @Test
    public void testBuscarClientePorId() throws Exception {
        // Given
        ClienteListagemDTO cliente = new ClienteListagemDTO(1L, "João Silva", 1000.0f);
        when(feignClient.getById(1L)).thenReturn(cliente);

        // When
        mockMvc.perform(get("/clientes/1"))
                // Then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("João Silva"))
                .andExpect(jsonPath("$.saldo").value(1000.0));

        verify(feignClient, times(1)).getById(1L);
    }

    @Test
    public void testCriarCliente() throws Exception {
        // Given
        ClienteCriacaoDTO clienteDto = new ClienteCriacaoDTO("João Silva", 1000.0f);
        Cliente cliente = ClienteMapper.toEntity(clienteDto);
        ClienteListagemDTO clienteCriado = new ClienteListagemDTO(1L, "João Silva", 1000.0f);
        when(feignClient.cadastrar(any(Cliente.class))).thenReturn(clienteCriado);

        // When
        mockMvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\":\"João Silva\",\"saldo\":1000.0}"))
                // Then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("João Silva"))
                .andExpect(jsonPath("$.saldo").value(1000.0));

        verify(feignClient, times(1)).cadastrar(any(Cliente.class));
    }

    @Test
    public void testAtualizarCliente() throws Exception {
        // Given
        ClienteCriacaoDTO clienteDto = new ClienteCriacaoDTO("João Silva Atualizado", 2000.0f);
        Cliente cliente = ClienteMapper.toEntity(clienteDto);
        cliente.setId(1L);
        ClienteListagemDTO clienteAtualizado = new ClienteListagemDTO(1L, "João Silva Atualizado", 2000.0f);
        when(feignClient.atualizar(eq(1L), any(Cliente.class))).thenReturn(clienteAtualizado);

        // When
        mockMvc.perform(put("/clientes/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\":\"João Silva Atualizado\",\"saldo\":2000.0}"))
                // Then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("João Silva Atualizado"))
                .andExpect(jsonPath("$.saldo").value(2000.0));

        verify(feignClient, times(1)).atualizar(eq(1L), any(Cliente.class));
    }

    @Test
    public void testDeletarCliente() throws Exception {
        // Given
        when(feignClient.getById(1L)).thenReturn(new ClienteListagemDTO(1L, "João Silva", 1000.0f));

        // When
        mockMvc.perform(delete("/clientes/1"))
                // Then
                .andExpect(status().isOk());

        verify(feignClient, times(1)).getById(1L);
        verify(feignClient, times(1)).deletar(1L);
    }

    @Test
    public void testDeletarClienteNaoEncontrado() throws Exception {
        // Given
        when(feignClient.getById(1L)).thenReturn(null);

        // When
        mockMvc.perform(delete("/clientes/1"))
                // Then
                .andExpect(status().isNotFound());

        verify(feignClient, times(1)).getById(1L);
        verify(feignClient, times(0)).deletar(1L);
    }

    @Test
    public void testCalcularScoreCredito() throws Exception {
        // Given
        ClienteListagemDTO cliente = new ClienteListagemDTO(1L, "João Silva", 1000.0f);
        when(feignClient.getById(1L)).thenReturn(cliente);
        when(clienteService.calcularScoreCredito(1000.0f)).thenReturn(800.0f);

        // When
        mockMvc.perform(get("/clientes/1/calcularScoreCredito"))
                // Then
                .andExpect(status().isOk())
                .andExpect(content().string("800.0"));

        verify(feignClient, times(1)).getById(1L);
        verify(clienteService, times(1)).calcularScoreCredito(1000.0f);
    }

    @Test
    public void testCalcularScoreCreditoClienteNaoEncontrado() throws Exception {
        // Given
        when(feignClient.getById(1L)).thenReturn(null);

        // When
        mockMvc.perform(get("/clientes/1/calcularScoreCredito"))
                // Then
                .andExpect(status().isNotFound());

        verify(feignClient, times(1)).getById(1L);
        verify(clienteService, times(0)).calcularScoreCredito((float) anyDouble());
    }
}