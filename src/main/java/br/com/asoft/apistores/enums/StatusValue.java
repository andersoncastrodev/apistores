package br.com.asoft.apistores.enums;

public enum StatusValue {

    ATIVO ("Ativo"),
    INATIVO ("Inativo");

    // ATIVO é a CONSTANTE do Enum que inicia com o numero 0 (Zero). E o nome da CONSTANTE do Enum é "Ativo".

    // INATIVO é a CONSTANTE do Enum que inicia com o numero 1 (Um). E o nome da CONSTANTE do Enum é "Inativo"

    // Obs: para pegar o nome da CONSTANTE do Enum, basta chamar o metodo
    // ' StatusValue.ATIVO.name()  ou StatusValue.INATIVO.name() '

    // Obs: Se tiver outras constantes no Enum. os numeros seria , 2, 3, 4, 5 etc..

    //Variavel de Instancia
    private String label;

    //Construtor do Enum (automaticamente é private)
    StatusValue(String label) {
        this.label = label;
    }

    //Getters Para Buscar o NOME da CONSTANTE do Enum.
    public String getLabel() {
        return label; // vai retornar o "Ativo ou Inativo" QUE é o NOME da CONSTANTE do enum.
        // COMO CHAMAR ' StatusValue.ATIVO.getLabel() ou StatusValue.INATIVO.getLabel() '
    }

}
