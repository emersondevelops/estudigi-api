package br.com.estudigi.api.model.enums;

public enum Role {
    ADMIN ("Administrador"),
    STUDENT ("Estudante"),
    TEACHER ("Professor(a)")
    ;

    private final String description;

    Role(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
