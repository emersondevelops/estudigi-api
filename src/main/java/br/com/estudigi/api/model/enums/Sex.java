package br.com.estudigi.api.model.enums;

public enum Sex {
    MALE ("Masculino"),
    FEMALE ("Feminino");

    private final String description;

    Sex(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
