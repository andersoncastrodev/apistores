package br.com.asoft.apistores.inp;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoInp {

    private Long id;

    @NotBlank
    private String rua;

    @NotBlank
    private String cep;

    @NotNull
    private CidadeIdInp cidade;
}
