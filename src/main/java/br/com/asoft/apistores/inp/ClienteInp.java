package br.com.asoft.apistores.inp;

import br.com.asoft.apistores.model.Pessoa;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteInp {

    private Long id;

    private String tipo;

//    private Pessoa pessoa;
    private PessoaIdInp pessoa;
}
