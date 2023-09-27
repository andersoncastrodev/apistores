package br.com.asoft.apistores.inp;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstadoIdInp {

    @NotNull
    private Long id;
}
