package br.com.asoft.apistores.enums;

public enum TypePerson {

    FISICA("Fisica"),

    JURIDICA("Juridica");

    private String label;

    TypePerson(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
