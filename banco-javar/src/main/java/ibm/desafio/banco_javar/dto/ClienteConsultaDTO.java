package ibm.desafio.banco_javar.dto;

import ibm.desafio.banco_javar.entity.Cliente;
import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ClienteConsultaDTO {
    private Long id;
    private String nome;
    private Long telefone;
    private Boolean correntista;
    private float saldo;

    public ClienteConsultaDTO(Cliente cliente) {
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.telefone = cliente.getTelefone();
        this.correntista = cliente.getCorrentista();
        this.saldo = cliente.getSaldo();
    }
}
