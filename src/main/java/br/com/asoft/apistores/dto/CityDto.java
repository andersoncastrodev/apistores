package br.com.asoft.apistores.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityDto {

    private Long id;

    private String name;

    private StateDto state;

}
