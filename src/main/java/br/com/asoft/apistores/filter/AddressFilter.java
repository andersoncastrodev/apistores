package br.com.asoft.apistores.filter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressFilter {

    private Long id;

    private String rua;

    private String cep;

    //Colocar os campos ( Atributos ) da Entidade City:
    private String nomecidade; //aqui temos que mudar pq
    // em StateValue tambem temos um "Nome"

    // E dentro de City Temos o StateValue, Colocar
    // os campos ( Atributos ).
    private String nomeestado;

    private String sigla;

    //Resumindo. Vamos pesquisa por id,rua e cep da
    // Entidade(Address)
    // e por nomeCidade da Entidade(City)
    // e por nomeEstado e Sigla da Entidade(StateValue.)

}
