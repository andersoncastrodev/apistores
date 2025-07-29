package br.com.asoft.apistores.filter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductFilter {

    private String description;

    private String barcode;

    private Long category;

    private Long supplier;
}
