package ibm.desafio.banco_javar.feign;

import ibm.desafio.banco_javar.dto.ClienteCriacaoDTO;
import ibm.desafio.banco_javar.dto.ClienteListagemDTO;
import ibm.desafio.banco_javar.entity.Cliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;

@Service
@FeignClient(
        value = "data-manager",
        url = "${spring.cloud.openfeign.client.config.data-manager.url}:8000",

        path = "/clientes")
public interface FeignClientBanco {
    @PostMapping
    ClienteListagemDTO cadastrar(ClienteCriacaoDTO cliente);

    @GetMapping
    List<ClienteListagemDTO> listar(Pageable paginacao);

    @PutMapping
    ClienteListagemDTO atualizar(Cliente dados);

    @DeleteMapping({"/{id}"})
    void deletar(@PathVariable Long id);

    @GetMapping({"/{id}"})
    ClienteListagemDTO getById(@PathVariable Long id);
}