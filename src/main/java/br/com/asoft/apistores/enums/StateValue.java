package br.com.asoft.apistores.enums;

public enum StateValue {
    AC("Ac"),
    AL("Al"),
    AP("Ap"),
    AM("Am"),
    BA("Ba"),
    CE("Ce"),
    DF("Df"),
    ES("Es"),
    GO("Go"),
    MA("Ma"),
    MT("Mt"),
    MS("Ms"),
    MG("Mg"),
    PA("Pa"),
    PB("Pb"),
    PR("Pr"),
    PE("Pe"),
    PI("Pi"),
    RJ("Rj"),
    RN("Rn"),
    RS("Rs"),
    RO("Ro"),
    RR("Rr"),
    SC("Sc"),
    SP("Sp"),
    SE("Se"),
    TO("To");

    private final String name;

    StateValue(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
