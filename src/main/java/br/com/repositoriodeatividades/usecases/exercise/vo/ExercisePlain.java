package br.com.repositoriodeatividades.usecases.exercise.vo;

import br.com.repositoriodeatividades.entities.User;
import br.com.repositoriodeatividades.usecases.exercise.enums.ExerciseLevelType;

/**
 * Created by jairomendes on 4/1/16.
 */
public class ExercisePlain {

    private String exerciseLabel;
    private String exerciseType;
    private ExerciseLevelType exerciseLevel;
    private String[] optionLabel;
    private String[] exerciseTags;
    private String username;

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

    public ExerciseLevelType getExerciseLevel() {
        return exerciseLevel;
    }

    public void setExerciseLevel(ExerciseLevelType exerciseLevel) {
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
}
