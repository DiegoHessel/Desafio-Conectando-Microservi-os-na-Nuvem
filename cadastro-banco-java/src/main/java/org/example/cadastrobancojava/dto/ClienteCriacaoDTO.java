package org.example.cadastrobancojava.dto;

import lombok.Data;

@Data
public class ClienteCriacaoDTO{

    private String nome;
    private Long telefone;
    private Boolean correntista;
    private float scoreCredito;
    private float saldo;
}
