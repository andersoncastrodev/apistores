package br.com.asoft.apistores.dto;


import br.com.asoft.apistores.enums.StateValue;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDto {

    private Long id;

    private String street;

    private String numbers;

    private String neighborhood;

    private String city;

    private StateValue state;

    private String cep;

    private String complement;

    private String typeAddress;

}
