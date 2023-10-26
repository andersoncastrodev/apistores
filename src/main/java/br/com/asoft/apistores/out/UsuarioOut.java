package br.com.asoft.apistores.out;

import br.com.asoft.apistores.model.Pessoa;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioOut {


    private Long id;

    private String login;

    private String senha;

    private Pessoa pessoa;

}
