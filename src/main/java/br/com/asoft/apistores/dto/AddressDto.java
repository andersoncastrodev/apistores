package br.com.asoft.apistores.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDto {

    private Long id;

    private String street;

    private String numbers;

    private String cep;

    private CityDto city;

}
