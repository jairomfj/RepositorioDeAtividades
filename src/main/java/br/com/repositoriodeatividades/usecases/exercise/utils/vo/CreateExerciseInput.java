package br.com.repositoriodeatividades.usecases.exercise.utils.vo;

import br.com.repositoriodeatividades.usecases.exercise.utils.enums.ExerciseLevelType;

public class CreateExerciseInput {

    private String exerciseLabel;
    private String exerciseType;
    private ExerciseLevelType exerciseLevel;
    private String[] exerciseTags;
    private Long userID;
    private String exerciseOptions;

    public CreateExerciseInput() {}

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


    public String[] getExerciseTags() {
        return exerciseTags;
    }

    public void setExerciseTags(String[] exerciseTags) {
        this.exerciseTags = exerciseTags;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getExerciseOptions() {
        return exerciseOptions;
    }

    public void setExerciseOptions(String exerciseOptions) {
        this.exerciseOptions = exerciseOptions;
    }
}
