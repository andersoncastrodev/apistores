package br.com.asoft.apistores.inp;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteInp {

    private Long id;

    @NotBlank
    private String tipo;

    @NotNull
    private PessoaIdInp pessoa;
}
