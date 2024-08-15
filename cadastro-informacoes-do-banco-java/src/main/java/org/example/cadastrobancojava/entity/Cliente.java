package org.example.cadastrobancojava.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import org.example.cadastrobancojava.dto.ClienteCriacaoDTO;

@Data
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    @NotNull
    @NotBlank
    private String nome;

    @NotNull
    @Column(name = "Telefone")
    private Long telefone;

    @NotNull
    @Column(name = "correntista")
    private Boolean correntista;

    @NotNull
    @PositiveOrZero
    @Column(name = "saldo_cc")
    private float saldo;

    private float scoreCredito;

    public Cliente(ClienteCriacaoDTO clienteDto) {
        this.nome = clienteDto.getNome();
        this.telefone = clienteDto.getTelefone();
        this.correntista = clienteDto.getCorrentista();
        this.saldo = clienteDto.getSaldo();
    }

    public Cliente(long id, String nome, Long telefone, Boolean correntista, float saldo) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.correntista = correntista;
        this.saldo = saldo;
    }

    public float getScoreCredito() {
        return saldo * 0.1f;
    }
}