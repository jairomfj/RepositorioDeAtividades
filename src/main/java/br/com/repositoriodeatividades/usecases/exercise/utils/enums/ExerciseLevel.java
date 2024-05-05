package br.com.repositoriodeatividades.usecases.exercise.utils.enums;


public enum ExerciseLevel {

    EASY(0),
    MEDIUM(1),
    HARD(2);

    private int level;

    ExerciseLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}
