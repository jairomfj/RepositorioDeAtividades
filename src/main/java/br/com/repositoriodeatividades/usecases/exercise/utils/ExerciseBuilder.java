package br.com.repositoriodeatividades.usecases.exercise.utils;

import br.com.repositoriodeatividades.usecases.exercise.utils.enums.ExerciseType;
import br.com.repositoriodeatividades.usecases.exercise.utils.interfaces.ExerciseItem;
import br.com.repositoriodeatividades.usecases.exercise.utils.vo.ExercisePlain;
import br.com.repositoriodeatividades.entities.Exercise;
import br.com.repositoriodeatividades.entities.ExerciseOption;
import br.com.repositoriodeatividades.usecases.util.RepositoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ExerciseBuilder {

    @Autowired
    RepositoryUtils repositoryUtils;

    public Exercise build(List<ExerciseItem> exerciseItemList) throws Exception {

        if(exerciseItemList == null) {
            throw new IllegalAccessException("ExerciseItemList cannot be null");
        }

        if(exerciseItemList.size() == 0) {
            throw new IllegalAccessException("ExerciseItemList cannot be null");
        }

        Exercise exercise = new Exercise();
        exercise.setType(ExerciseType.NO_CHOICE.toString());

        List<ExerciseOption> exerciseOptions = new ArrayList<>();
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

    public Exercise build(ExercisePlain exercisePlain) {
        List<ExerciseOption> exerciseOptionList = new ArrayList<>();
        String[] optionLabels = exercisePlain.getOptionLabel();
        if(optionLabels != null && optionLabels.length > 0) {
            for(String optionLabel : optionLabels) {
                optionLabel = repositoryUtils.extractEnumerationFromString(optionLabel.trim()).trim();

                if(optionLabel.equals("") || optionLabel.equals(null))
                    continue;

                ExerciseOption exerciseOption = new ExerciseOption();
                exerciseOption.setLabel(optionLabel);
                exerciseOptionList.add(exerciseOption);
            }
        }

        String exerciseLabel = repositoryUtils.extractEnumerationFromString(exercisePlain.getExerciseLabel().trim()).trim();
        exercisePlain.setExerciseLabel(exerciseLabel);
        return new Exercise(exercisePlain, exerciseOptionList);
    }

}
