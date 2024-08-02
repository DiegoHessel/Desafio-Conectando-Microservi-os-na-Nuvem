package ibm.desafio.banco_javar.controller;

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
    public ResponseEntity<List<Cliente>> listarClientes() {
        List<Cliente> clientes = clienteService.listarClientes();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarClientePorId(@PathVariable Long id) {
        Cliente cliente = clienteService.buscarClientePorId(id);
        return ResponseEntity.ok(cliente);

    }

    @PostMapping
    public ResponseEntity<Cliente> criarCliente(@RequestBody @Valid Cliente cliente) {
        if (cliente.getId() != null) {
            return ResponseEntity.badRequest().build();
        }
        Cliente clienteCriado = clienteService.criarCliente(cliente);
        return ResponseEntity.ok(clienteCriado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizarCliente(@PathVariable Long id, @RequestBody @Valid Cliente cliente) {
        if (!id.equals(cliente.getId())) {
            return ResponseEntity.badRequest().build();
        }
        Cliente clienteAtualizado = clienteService.atualizarCliente(id, cliente);
        return ResponseEntity.ok(clienteAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id) {
        if (clienteService.buscarClientePorId(id) == null) {
            return ResponseEntity.notFound().build();
        }
        else {
            clienteService.deletarCliente(id);
            return ResponseEntity.ok().build();
        }
    }

    @GetMapping("/{id}/calcularScoreCredito")
    public Float calcularScoreCredito(@PathVariable Long id) {
        Cliente cliente = clienteService.buscarClientePorId(id);
        if (cliente != null) {
            return cliente.getSaldo() * 0.1f;
        } else {
            throw new EntidadeNaoEncontradaException("Cliente não encontrado!");
        }
    }
}
