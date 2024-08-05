package ibm.desafio.banco_javar.controller;

import ibm.desafio.banco_javar.dto.ClienteCriacaoDTO;
import ibm.desafio.banco_javar.dto.ClienteListagemDTO;
import ibm.desafio.banco_javar.dto.ClienteMapper;
import ibm.desafio.banco_javar.entity.Cliente;
import ibm.desafio.banco_javar.exception.EntidadeNaoEncontradaException;
import ibm.desafio.banco_javar.feign.FeignClientBanco;
import ibm.desafio.banco_javar.service.ClienteServiceApi;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.awt.print.Pageable;
import java.util.List;

@RestController()
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private  ClienteServiceApi clienteService;
    @Autowired
    private  FeignClientBanco feignClient;

    @GetMapping
    public ResponseEntity<List<ClienteListagemDTO>> listarClientes(Pageable paginacao) {
        List<ClienteListagemDTO> clientes = feignClient.listar((Cliente) paginacao);
        if (clientes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<ClienteListagemDTO> dtos = ClienteMapper.toDto(clientes);
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteListagemDTO> buscarClientePorId(@PathVariable Long id) {
        ClienteListagemDTO cliente = feignClient.getById(id);
        ClienteListagemDTO dto = ClienteMapper.toDTO(cliente);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<ClienteListagemDTO> criarCliente(@RequestBody @Valid ClienteCriacaoDTO clienteDto) {
        Cliente cliente = ClienteMapper.toEntity(clienteDto);
        ClienteListagemDTO clienteCriado = feignClient.cadastrar(cliente);
        return ResponseEntity.ok(clienteCriado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteListagemDTO> atualizarCliente(@PathVariable Long id, @RequestBody @Valid ClienteCriacaoDTO clienteDto) {
        Cliente cliente = ClienteMapper.toEntity(clienteDto);
        cliente.setId(id);
        ClienteListagemDTO clienteAtualizado = feignClient.atualizar(id, cliente);
        return ResponseEntity.ok(clienteAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id) {
        if (feignClient.getById(id) == null) {
            return ResponseEntity.notFound().build();
        } else {
            feignClient.deletar(id);
            return ResponseEntity.ok().build();
        }
    }

    @GetMapping("/{id}/calcularScoreCredito")
    public Float calcularScoreCredito(@PathVariable Long id) {
        ClienteListagemDTO cliente = feignClient.getById(id);
        if (cliente != null) {
            return clienteService.calcularScoreCredito(cliente.getSaldo());
        } else {
            throw new EntidadeNaoEncontradaException("Cliente não encontrado!");
        }
    }
}