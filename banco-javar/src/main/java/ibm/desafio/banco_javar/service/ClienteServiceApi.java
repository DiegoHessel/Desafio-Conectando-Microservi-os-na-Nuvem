package ibm.desafio.banco_javar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClienteServiceApi {

    public  Float  calcularScoreCredito(Float saldo){
        return saldo * 0.1f;
    }

}
