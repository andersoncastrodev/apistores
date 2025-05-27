package br.com.asoft.apistores.enums;

public enum RolesValue {

    ADMIN(1L),

    USER(2L);

    private Long id_role;

    RolesValue(Long id_role) {
        this.id_role = id_role;
    }

    public Long getIdRole() {
        return id_role;
    }
}
