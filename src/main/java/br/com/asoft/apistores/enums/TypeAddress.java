package br.com.asoft.apistores.enums;

public enum TypeAddress {

    RESIDENCIAL ("Residencial"),
    COMERCIAL ("Comercial");

    private String label;

    TypeAddress(String label) {
        this.label = label;
    }
    public String getLabel() {
        return label;
    }
}
