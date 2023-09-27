package br.com.asoft.apistores.inp;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeInp {

    @NotBlank
    private String nome;

    @NotNull
    private EstadoIdInp estado;
}
