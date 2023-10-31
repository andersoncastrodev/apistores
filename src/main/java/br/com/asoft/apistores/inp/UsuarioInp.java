package br.com.asoft.apistores.inp;

import br.com.asoft.apistores.model.Pessoa;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioInp {

    private Long id;

    @NotBlank
    private String login;

    @NotBlank
    private String senha;

    @NotNull
    private Pessoa pessoa;

}
