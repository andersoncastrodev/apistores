package br.com.asoft.apistores.filter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SupplierFilter {

    private String name;

    private String cpfCnpj;

    private String email;
}
