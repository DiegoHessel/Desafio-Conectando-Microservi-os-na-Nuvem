package org.example.cadastrobancojava.dto;

import lombok.Data;
import org.example.cadastrobancojava.entity.Cliente;

@Data
public class DadosClienteListagemDTO {
    private Long id;
    private String nome;
    private Long telefone;
    private Boolean correntista;
    private float saldo;

    public DadosClienteListagemDTO(Long id, String nome, Long telefone, Boolean correntista, float saldo) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.correntista = correntista;
        this.saldo = saldo;
    }

    public DadosClienteListagemDTO(Cliente cliente) {
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.telefone = cliente.getTelefone();
        this.correntista = cliente.getCorrentista();
        this.saldo = cliente.getSaldo();
    }

}