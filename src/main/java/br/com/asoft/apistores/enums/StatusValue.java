package br.com.asoft.apistores.enums;

public enum StatusValue {

    ATIVO ("Ativo"),
    INATIVO ("Inativo");

    private String description;

    StatusValue(String description) {
        this.description = description;
    }
}
