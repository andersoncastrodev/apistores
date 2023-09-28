package br.com.asoft.apistores.inp;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstadoInp {

    @NotBlank
    private String nome;

    @NotBlank
    private String sigla;
}
