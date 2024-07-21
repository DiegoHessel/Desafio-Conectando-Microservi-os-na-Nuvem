package ibm.desafio.banco_javar.entity;

import jakarta.persistence.*;
import lombok.*;

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
    private String nome;

    @Column(name = "Telefone")
    private Long telefone;

    @Column(name = "correntista")
    private Boolean correntista;

    @Column(name = "saldo_cc")
    private float saldo;

    public float getScoreCredito() {
        return saldo = this.saldo * 0.1f;

    }

}
