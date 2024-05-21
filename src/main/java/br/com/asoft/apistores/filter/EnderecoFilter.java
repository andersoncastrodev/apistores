package br.com.asoft.apistores.filter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoFilter {

    private Long id;

    private String rua;

    private String cep;

    //Colocar os campos ( Atributos ) da Entidade Cidade:
    private String nomecidade; //aqui temos que mudar pq
    // em Estado tambem temos um "Nome"

    // E dentro de Cidade Temos o Estado, Colocar
    // os campos ( Atributos ).
    private String nomeestado;

    private String sigla;

    //Resumindo. Vamos pesquisa por id,rua e cep da
    // Entidade(Endereco)
    // e por nomeCidade da Entidade(Cidade)
    // e por nomeEstado e Sigla da Entidade(Estado.)

}
