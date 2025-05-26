package br.com.asoft.apistores.enums;

public enum SexValue {

    MASCULINO ("Masculino"),
    FEMININO ("Feminino");

    private String description;

    SexValue(String description) {
        this.description = description;
    }
}
