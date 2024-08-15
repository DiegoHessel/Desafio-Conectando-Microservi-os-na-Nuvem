package ibm.desafio.banco_javar.feign;

import ibm.desafio.banco_javar.dto.ClienteListagemDTO;
import ibm.desafio.banco_javar.entity.Cliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@FeignClient(
        url = "http://localhost:8001/clientes",
        name = "cadastro-banco-java")
public interface FeignClientBanco {

    @GetMapping()
    List<ClienteListagemDTO> listar();

    @GetMapping({"/{id}"})
    ClienteListagemDTO getById(@PathVariable Long id);

    @PutMapping({"/{id}"})
    ClienteListagemDTO atualizar(@PathVariable Long id, Cliente dados);


    @PostMapping
    ClienteListagemDTO cadastrar(Cliente cliente);

    @DeleteMapping({"/{id}"})
    void deletar(@PathVariable Long id);


}