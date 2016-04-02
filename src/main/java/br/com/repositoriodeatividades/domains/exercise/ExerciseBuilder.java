package br.com.repositoriodeatividades.domains.exercise;

import br.com.repositoriodeatividades.domains.exercise.enums.ExerciseType;
import br.com.repositoriodeatividades.domains.interfaces.ExerciseItem;
import br.com.repositoriodeatividades.entities.Exercise;
import br.com.repositoriodeatividades.entities.ExerciseOption;

import java.util.ArrayList;
import java.util.List;

public class ExerciseBuilder {

    public Exercise build(List<ExerciseItem> exerciseItemList) throws Exception {

        if(exerciseItemList == null) {
            throw new IllegalAccessException("ExerciseItemList cannot be null");
        }

        if(exerciseItemList.size() == 0) {
            throw new IllegalAccessException("ExerciseItemList cannot be null");
        }

        Exercise exercise = new Exercise();
        exercise.setType(ExerciseType.DEFAULT.toString());


        List<ExerciseOption> exerciseOptions = new ArrayList<ExerciseOption>();
        for(ExerciseItem exerciseItem : exerciseItemList) {
            if(exercise.getLabel() == null) {
                exercise.setLabel(exerciseItem.getLabel());
            } else {
                exercise.setType(ExerciseType.MULTIPLE_CHOICE.toString());
                ExerciseOption exerciseOption = new ExerciseOption();
                exerciseOption.setExercise(exercise);
                exerciseOption.setLabel(exerciseItem.getLabel());
                exerciseOptions.add(exerciseOption);
                exercise.setExerciseOptions(exerciseOptions);
            }
        }

        return exercise;
    }

}
