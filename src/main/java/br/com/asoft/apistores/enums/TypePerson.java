package br.com.asoft.apistores.enums;

public enum TypePerson {

    FISICA("Fisica"),

    JURIDICA("Juridica");

    private String description;

    TypePerson(String description) {
        this.description = description;
    }
}
