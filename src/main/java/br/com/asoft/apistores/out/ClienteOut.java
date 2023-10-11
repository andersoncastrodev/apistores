package br.com.asoft.apistores.out;

import br.com.asoft.apistores.model.Pessoa;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteOut {

    private Long id;

    private String tipo;

    private Pessoa pessoa;
}
