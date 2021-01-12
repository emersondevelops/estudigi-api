package br.com.estudigi.api.model.enums;

import lombok.Getter;

@Getter
public enum Grade {
    SIXTH("6º", 6),
    SEVENTH("7º", 7),
    EIGHTH("8º", 8),
    NINTH("9º", 9),
    MIXED("Misto", 0);

    private final String description;
    private final Integer gradeNumber;

    Grade(String description, Integer gradeNumber) {

        this.description = description;
        this.gradeNumber = gradeNumber;
    }
}
