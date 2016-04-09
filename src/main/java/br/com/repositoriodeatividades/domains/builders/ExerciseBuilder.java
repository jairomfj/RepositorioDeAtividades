package br.com.repositoriodeatividades.domains.builders;

import br.com.repositoriodeatividades.domains.enums.ExerciseType;
import br.com.repositoriodeatividades.domains.interfaces.ExerciseItem;
import br.com.repositoriodeatividades.domains.vo.ExercisePlain;
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
        exercise.setType(ExerciseType.NO_CHOICE.toString());

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

    public Exercise build(ExercisePlain exercisePlain, String[] optionLabels) {
        List<ExerciseOption> exerciseOptionList = new ArrayList<ExerciseOption>();
        if(optionLabels.length > 0) {
            for(String optionLabel : optionLabels) {
                optionLabel = optionLabel.trim();

                if(optionLabel.equals("") || optionLabel.equals(null))
                    continue;

                ExerciseOption exerciseOption = new ExerciseOption();
                exerciseOption.setLabel(optionLabel);
                exerciseOptionList.add(exerciseOption);
            }
        }
        return new Exercise(exercisePlain, exerciseOptionList);
    }

}
