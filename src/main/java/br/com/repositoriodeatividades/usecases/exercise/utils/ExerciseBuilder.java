package br.com.repositoriodeatividades.usecases.exercise.utils;

import br.com.repositoriodeatividades.entities.ExerciseEntity;
import br.com.repositoriodeatividades.entities.ExerciseOptionEntity;
import br.com.repositoriodeatividades.usecases.exercise.utils.enums.ExerciseType;
import br.com.repositoriodeatividades.usecases.exercise.utils.interfaces.ExerciseItem;
import br.com.repositoriodeatividades.usecases.exercise.utils.vo.CreateExerciseInput;
import br.com.repositoriodeatividades.usecases.exercise.utils.vo.ExercisePlain;
import br.com.repositoriodeatividades.usecases.util.RepositoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ExerciseBuilder {

    @Autowired
    RepositoryUtils repositoryUtils;

    public ExerciseEntity build(List<ExerciseItem> exerciseItemList) throws Exception {
        if (exerciseItemList == null) {
            throw new IllegalAccessException("ExerciseItemList cannot be null");
        }

        if (exerciseItemList.size() == 0) {
            throw new IllegalAccessException("ExerciseItemList cannot be null");
        }

        ExerciseEntity exercise = new ExerciseEntity();
        exercise.setType(ExerciseType.UNKNOWN.toString());
        exercise.setLabel(exerciseItemList.get(0).getLabel());

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i < exerciseItemList.size(); i++) {
            stringBuilder.append(exerciseItemList.get(i).getLabel());
            stringBuilder.append("\n");
        }

        exercise.setOptions(stringBuilder.toString());

        return exercise;
    }

    public ExerciseEntity build(CreateExerciseInput createExerciseInput) {
        List<ExerciseOptionEntity> exerciseOptionList = new ArrayList<>();
        String[] optionLabels = createExerciseInput.getExerciseOptions().split("\n");
        if (optionLabels != null && optionLabels.length > 0) {
            for (String optionLabel : optionLabels) {
                optionLabel = repositoryUtils.extractEnumerationFromString(optionLabel.trim()).trim();
                if (optionLabel.equals(""))
                    continue;

                ExerciseOptionEntity exerciseOption = new ExerciseOptionEntity();
                exerciseOption.setLabel(optionLabel);
                exerciseOptionList.add(exerciseOption);
            }
        }

        String exerciseLabel = repositoryUtils.extractEnumerationFromString(createExerciseInput.getExerciseLabel().trim()).trim();
        createExerciseInput.setExerciseLabel(exerciseLabel);
        return new ExerciseEntity(createExerciseInput, exerciseOptionList);
    }

}
