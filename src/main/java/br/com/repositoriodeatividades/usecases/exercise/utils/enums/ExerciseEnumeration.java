package br.com.repositoriodeatividades.usecases.exercise.utils.enums;

public enum ExerciseEnumeration {

    BULLET("^\\u2022"),
    NUMBER_BAR("^\\d*\\)"),
    NUMBER_DOT("^\\d*\\."),
    NUMBER_HYPHEN("^\\d*-"),
    CHAR_BAR("^\\A[^\\W\\d_]{1}\\)"),
    CHAR_DOT("^\\A[^\\W\\d_]{1}\\."),
    CHAR_HYPHEN("^\\A[^\\W\\d_]{1}-"),
    PARENTHESIS("\\(\\)"),
    NONE("ˆ\\w");

    private String fullEnumeration;

    ExerciseEnumeration (String fullEnumeration) {
        this.fullEnumeration = fullEnumeration;
    }

    public String getFullEnumeration() {
        return fullEnumeration;
    }

}
