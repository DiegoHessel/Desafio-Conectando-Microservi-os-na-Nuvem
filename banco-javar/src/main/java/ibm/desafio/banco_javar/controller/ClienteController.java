package ibm.desafio.banco_javar.controller;

import ibm.desafio.banco_javar.dto.ClienteCriacaoDTO;
import ibm.desafio.banco_javar.dto.ClienteListagemDTO;
import ibm.desafio.banco_javar.dto.ClienteMapper;
import ibm.desafio.banco_javar.entity.Cliente;
import ibm.desafio.banco_javar.exception.EntidadeNaoEncontradaException;
import ibm.desafio.banco_javar.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<ClienteListagemDTO>> listarClientes() {
        List<Cliente> clientes = clienteService.listarClientes();
        if (clientes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<ClienteListagemDTO> dtos = ClienteMapper.toDto(clientes);
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteListagemDTO> buscarClientePorId(@PathVariable Long id) {
        Cliente cliente = clienteService.buscarClientePorId(id);
        ClienteListagemDTO dto = ClienteMapper.toDTO(cliente);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<Cliente> criarCliente(@RequestBody @Valid ClienteCriacaoDTO clienteDto) {
        Cliente cliente = ClienteMapper.toEntity(clienteDto);
        Cliente clienteCriado = clienteService.criarCliente(cliente);
        return ResponseEntity.ok(clienteCriado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizarCliente(@PathVariable Long id, @RequestBody @Valid ClienteCriacaoDTO clienteDto) {
        Cliente cliente = ClienteMapper.toEntity(clienteDto);
        cliente.setId(id);
        Cliente clienteAtualizado = clienteService.atualizarCliente(id, cliente);
        return ResponseEntity.ok(clienteAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id) {
        if (clienteService.buscarClientePorId(id) == null) {
            return ResponseEntity.notFound().build();
        } else {
            clienteService.deletarCliente(id);
            return ResponseEntity.ok().build();
        }
    }

    @GetMapping("/{id}/calcularScoreCredito")
    public Float calcularScoreCredito(@PathVariable Long id) {
        Cliente cliente = clienteService.buscarClientePorId(id);
        if (cliente != null) {
            return clienteService.calcularScoreCredito(cliente.getSaldo());
        } else {
            throw new EntidadeNaoEncontradaException("Cliente não encontrado!");
        }
    }
}