package org.example.cadastrobancojava.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import org.example.cadastrobancojava.dto.ClienteCriacaoDTO;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    public Cliente(ClienteCriacaoDTO clienteDto) {
        this.nome = clienteDto.getNome();
        this.telefone = clienteDto.getTelefone();
        this.correntista = clienteDto.getCorrentista();
        this.saldo = clienteDto.getSaldo();
    }

    public float getScoreCredito() {
        return saldo = this.saldo * 0.1f;

    }

}
