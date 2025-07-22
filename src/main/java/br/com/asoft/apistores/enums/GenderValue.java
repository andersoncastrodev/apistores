package br.com.asoft.apistores.enums;

public enum GenderValue { //Enum Generos

    MASCULINO ("Masculino"),
    FEMININO ("Feminino"),
    OUTRO ("Outro");

    private String label;

    GenderValue(String description) {
        this.label = description;
    }

    public String getLabel() {
        return label;
    }
}
