package br.com.asoft.apistores.enums;

public enum TypePayment {

    DINHEIRO ("Dinheiro"),
    CARTAO  ("Cartao"),
    PIX     ("Pix"),
    BOLETO  ("Boleto");

    private String description;

    TypePayment(String description) {
        this.description = description;
    }

}
