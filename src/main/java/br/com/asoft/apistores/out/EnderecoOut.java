package br.com.asoft.apistores.out;

import br.com.asoft.apistores.model.City;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EnderecoOut {

    private Long id;
    private String rua;
    private String cep;
    private City city;
}
