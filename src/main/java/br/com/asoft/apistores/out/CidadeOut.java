package br.com.asoft.apistores.out;

import br.com.asoft.apistores.model.State;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeOut {

    private Long id;
    private String nome;
    private State state;
}
