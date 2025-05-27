package br.com.asoft.apistores.enums;

public enum TypePayment {

    DINHEIRO ("Dinheiro"),
    CARTAO  ("Cartao"),
    PIX     ("Pix"),
    BOLETO  ("Boleto");

    private String label;

    TypePayment(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}
