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
        url = "http://localhost:8000/clientes",
        name = "/clientes")
public interface FeignClientBanco {

    @GetMapping("/json")
    List<ClienteListagemDTO> listar(Pageable paginacao);

    @GetMapping({"/{id}/json"})
    ClienteListagemDTO getById(@PathVariable Long id);

    @PutMapping
    ClienteListagemDTO atualizar(Cliente dados);


    @PostMapping
    ClienteListagemDTO cadastrar(ClienteCriacaoDTO cliente);

    @DeleteMapping({"/{id}"})
    void deletar(@PathVariable Long id);


}