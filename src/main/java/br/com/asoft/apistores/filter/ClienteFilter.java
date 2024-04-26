package br.com.asoft.apistores.filter;

import br.com.asoft.apistores.model.Pessoa;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteFilter {

    private Long id;

    private String tipo;

    private Pessoa pessoa;
}
