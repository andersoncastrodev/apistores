package br.com.asoft.apistores.filter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteFilter {

    private Long id;

    private String tipo;

    //[ NÃO PODE FAZER a REFERENCIA DE CLASSE Assim no Filtro]
    // Não funcionar assim.

    //private Pessoa pessoa;

    // Atribuitos que tem dentro da classe PESSOA.
    //[ Dentro da classe Pessoa tenho um atributo "nome "]

    private String nome; //Atributo da classe Pessoa.

    private String telefone; //Atributo da classe Pessoa.

    //Se tiver Mais é só ir Adicionando.

}
