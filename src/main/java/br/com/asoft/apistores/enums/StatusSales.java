package br.com.asoft.apistores.enums;

public enum StatusSales {

    PENDENTE ("Pendente"),
    PAGO ("Pago"),
    CANCELADO ("Cancelado");

    private String description;

    StatusSales(String description) {
        this.description = description;
    }

}
