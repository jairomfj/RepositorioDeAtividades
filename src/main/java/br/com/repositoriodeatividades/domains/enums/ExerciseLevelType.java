package br.com.repositoriodeatividades.domains.enums;


public enum ExerciseLevelType {

    EASY(0),
    MEDIUM(1),
    HARD(2);

    private int level;

    ExerciseLevelType(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}
