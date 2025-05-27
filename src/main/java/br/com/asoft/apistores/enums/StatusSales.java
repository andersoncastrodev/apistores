package br.com.asoft.apistores.enums;

public enum StatusSales {

    PENDENTE ("Pendente"),
    PAGO ("Pago"),
    CANCELADO ("Cancelado");

    private String label;

    StatusSales(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}
