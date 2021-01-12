package br.com.estudigi.api.model.enums;

import lombok.Getter;

@Getter
public enum Subject {
    SCIENCE("Ciências da Natureza"),
    MATH("Matemática");

    private final String description;

    Subject(String description) {
        this.description = description;
    }
}
