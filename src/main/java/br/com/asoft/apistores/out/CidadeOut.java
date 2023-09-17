package br.com.asoft.apistores.out;

import br.com.asoft.apistores.model.Estado;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeOut {

    private Long id;
    private String nome;
    private Estado estado;
}
