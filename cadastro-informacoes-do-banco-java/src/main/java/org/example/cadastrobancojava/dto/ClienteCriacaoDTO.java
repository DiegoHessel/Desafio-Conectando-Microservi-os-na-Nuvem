package org.example.cadastrobancojava.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.cadastrobancojava.entity.Cliente;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteCriacaoDTO {
    private String nome;
    private Long telefone;
    private Boolean correntista;
    private float saldo;

    public ClienteCriacaoDTO(Object o, String nome, String telefone) {
        this.nome = nome;
        this.telefone = Long.parseLong(telefone);
        this.correntista = Boolean.TRUE; // ou outro valor padrão
        this.saldo = 0.0f; // ou outro valor padrão
    }

    public ClienteCriacaoDTO(Cliente cliente) {
        this.nome = cliente.getNome();
        this.telefone = cliente.getTelefone();
        this.correntista = cliente.getCorrentista();
        this.saldo = cliente.getSaldo();
    }
}