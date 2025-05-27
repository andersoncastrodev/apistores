package br.com.asoft.apistores.enums;

public enum SexValue {

    MASCULINO ("Masculino"),
    FEMININO ("Feminino");

    private String label;

    SexValue(String description) {
        this.label = description;
    }

    public String getLabel() {
        return label;
    }
}
