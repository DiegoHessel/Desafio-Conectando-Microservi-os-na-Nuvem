package ibm.desafio.banco_javar.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ClienteCriacaoDto {
    @NotBlank
    @NotNull
    @Size(min = 3, max = 100)
    private String nome;
    @NotNull
    @Size(min = 11, max = 11)
    private Long telefone;
    @NotNull
    private Boolean correntista;
    @NotNull
    private float scoreCredito;
    @NotNull
    private float saldo;
}
