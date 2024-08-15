package ibm.desafio.banco_javar.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    private Long id;
    private String nome;
    private Long telefone;
    private Boolean correntista;
    private float saldo;

    public Cliente(long id, String nome, float saldo) {
        this.id = id;
        this.nome = nome;
        this.saldo = saldo;
    }

    public float getScoreCredito() {
        return saldo = this.saldo * 0.1f;

    }

}
