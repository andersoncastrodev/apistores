package br.com.asoft.apistores.out;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteOut {

    private Long id;

    private String tipo;

    private Pessoa pessoa;
}
