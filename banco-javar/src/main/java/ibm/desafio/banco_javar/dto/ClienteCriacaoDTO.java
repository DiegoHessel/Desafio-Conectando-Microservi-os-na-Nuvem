package ibm.desafio.banco_javar.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ClienteCriacaoDTO{

    private String nome;
    private Long telefone;
    private Boolean correntista;
    private float scoreCredito;
    private float saldo;
}
