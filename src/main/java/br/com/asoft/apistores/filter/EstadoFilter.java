package br.com.asoft.apistores.filter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstadoFilter {

    //Campos da Entidade State
    private Long id;

    private String nome;

    private String sigla;
}
