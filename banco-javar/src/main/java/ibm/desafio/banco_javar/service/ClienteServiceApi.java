package ibm.desafio.banco_javar.service;

import ibm.desafio.banco_javar.dto.ClienteListagemDTO;
import ibm.desafio.banco_javar.feign.FeignClientBanco;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteServiceApi {
    private final FeignClientBanco feignClientBanco;

    public Float calcularScoreCredito(Float saldo) {
        return saldo * 0.1f;
    }

    public List<ClienteListagemDTO> listarClientes() {
        return feignClientBanco.listar();
    }
}