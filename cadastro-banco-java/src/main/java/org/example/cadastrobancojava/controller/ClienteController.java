package org.example.cadastrobancojava.controller;

import lombok.RequiredArgsConstructor;
import org.example.cadastrobancojava.dto.ClienteCriacaoDTO;
import org.example.cadastrobancojava.dto.DadosClienteListagemDTO;
import org.example.cadastrobancojava.service.ClienteService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping
    public List<DadosClienteListagemDTO> listarClientes(Pageable pageable) {
        return new ArrayList<>(clienteService.listarClientes(pageable));
    }

    @GetMapping("/{id}")
    public DadosClienteListagemDTO buscarClientePorId(@PathVariable Long id) {
        return clienteService.buscarClientePorId(id);
    }

    @PostMapping
    public DadosClienteListagemDTO criarCliente(@RequestBody ClienteCriacaoDTO dadosCadastrais) {
        return clienteService.criarCliente(dadosCadastrais);
    }

    @PutMapping("/{id}")
    public DadosClienteListagemDTO atualizarCliente(@RequestBody ClienteCriacaoDTO dadosCadastrais, @PathVariable Long id) {
        return clienteService.atualizarCliente(id, dadosCadastrais);
    }

    @DeleteMapping("/{id}")
    public void deletarCliente(@PathVariable Long id) {
        clienteService.deletarCliente(id);
    }
}