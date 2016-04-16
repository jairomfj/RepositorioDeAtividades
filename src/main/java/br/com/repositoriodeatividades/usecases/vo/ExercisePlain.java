package br.com.repositoriodeatividades.usecases.vo;

import br.com.repositoriodeatividades.usecases.enums.ExerciseLevelType;

/**
 * Created by jairomendes on 4/1/16.
 */
public class ExercisePlain {

    private String exerciseLabel;
    private String exerciseType;
    private ExerciseLevelType exerciseLevel;

    public ExercisePlain() {}

    public ExercisePlain(String exerciseLabel, String exerciseType, String exerciseLevel) {
        this.exerciseLabel = exerciseLabel;
        this.exerciseType  = exerciseType;
        this.exerciseLevel = ExerciseLevelType.valueOf(exerciseLevel);
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

    public ExerciseLevelType getExerciseLevel() {
        return exerciseLevel;
    }

    public void setExerciseLevel(ExerciseLevelType exerciseLevel) {
        this.exerciseLevel = exerciseLevel;
    }
}
