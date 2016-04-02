package br.com.repositoriodeatividades.domains.exercise.vo;

import br.com.repositoriodeatividades.entities.Exercise;

/**
 * Created by jairomendes on 4/1/16.
 */
public class ExercisePlain {

    private String exerciseLabel;

    private String exerciseType;

    public ExercisePlain() {}

    public ExercisePlain(String exerciseLabel, String exerciseType) {
        this.exerciseLabel = exerciseLabel;
        this.exerciseType  = exerciseType;
    }

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
}
