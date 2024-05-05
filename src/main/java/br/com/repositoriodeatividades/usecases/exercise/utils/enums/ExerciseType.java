package br.com.repositoriodeatividades.usecases.exercise.utils.enums;

/**
 * Created by jairomendes on 3/31/16.
 */
public enum ExerciseType {

    UNKNOWN(0),
    NO_CHOICE(1),
    MULTIPLE_CHOICE(2);

    private final int code;

    ExerciseType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
