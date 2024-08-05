package ibm.desafio.banco_javar.dto;

import lombok.Data;

@Data
public class ClienteListagemDTO {
    private Long id;
    private String nome;
    private Long telefone;
    private Boolean correntista;
    private float saldo;
}
