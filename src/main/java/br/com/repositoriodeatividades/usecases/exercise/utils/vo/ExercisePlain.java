package br.com.repositoriodeatividades.usecases.exercise.utils.vo;

import br.com.repositoriodeatividades.usecases.exercise.utils.enums.ExerciseLevel;

public class ExercisePlain {

    private Long exerciseId;
    private String exerciseLabel;
    private String exerciseType;
    private ExerciseLevel exerciseLevel;
    private String[] optionLabel;
    private String[] exerciseTags;
    private String username;
    private String exerciseOptions;

    public ExercisePlain() {}

    public String getExerciseLabel() {
        return exerciseLabel;
    }

    public void setExerciseLabel(String exerciseLabel) {
        this.exerciseLabel = exerciseLabel;
    }

    public String getExerciseType() {
        return exerciseType;
    }

    public void setExerciseType(String exerciseType) {
        this.exerciseType = exerciseType;
    }

    public ExerciseLevel getExerciseLevel() {
        return exerciseLevel;
    }

    public void setExerciseLevel(ExerciseLevel exerciseLevel) {
        this.exerciseLevel = exerciseLevel;
    }

    public String[] getOptionLabel() {
        return optionLabel;
    }

    public void setOptionLabel(String[] optionLabel) {
        this.optionLabel = optionLabel;
    }

    public String[] getExerciseTags() {
        return exerciseTags;
    }

    public void setExerciseTags(String[] exerciseTags) {
        this.exerciseTags = exerciseTags;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(Long exerciseId) {
        this.exerciseId = exerciseId;
    }

    public String getExerciseOptions() {
        return exerciseOptions;
    }

    public void setExerciseOptions(String exerciseOptions) {
        this.exerciseOptions = exerciseOptions;
    }
}
