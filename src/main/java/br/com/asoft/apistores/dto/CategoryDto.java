package br.com.asoft.apistores.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CategoryDto {
    private Long id;

    private String description;

    private LocalDateTime dateRegister;

    private LocalDateTime dateUpdate;
}
