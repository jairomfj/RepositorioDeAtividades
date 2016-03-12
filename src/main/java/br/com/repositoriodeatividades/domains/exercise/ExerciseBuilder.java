package br.com.repositoriodeatividades.domains.exercise;

import br.com.repositoriodeatividades.domains.interfaces.ExerciseItem;
import br.com.repositoriodeatividades.domains.util.RepositoryUtils;
import br.com.repositoriodeatividades.entities.Exercise;
import br.com.repositoriodeatividades.entities.ExerciseOption;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ExerciseBuilder {

    public List<Exercise> build(List<List<ExerciseItem>> exerciseItemList) throws Exception {

        if(exerciseItemList == null) {
            throw new IllegalAccessException("ExerciseItemList cannot be null");
        }

        if(exerciseItemList.size() == 0) {
            throw new IllegalAccessException("ExerciseItemList cannot be null");
        }


        //TODO remove enumeration from labels
        List<Exercise> exerciseList = new ArrayList<Exercise>();
        for(List<ExerciseItem> individualExerciseList : exerciseItemList) {
            Exercise exercise = new Exercise();
            List<ExerciseOption> exerciseOptions = new ArrayList<ExerciseOption>();
            for(ExerciseItem exerciseItem : individualExerciseList) {
                if(exercise.getLabel() == null) {
                    exercise.setLabel(exerciseItem.getLabel());
                } else {
                    ExerciseOption exerciseOption = new ExerciseOption();
                    exerciseOption.setExercise(exercise);
                    exerciseOption.setLabel(exerciseItem.getLabel());
                    exerciseOptions.add(exerciseOption);
                    exercise.setExerciseOptions(exerciseOptions);
                }
            }
            exerciseList.add(exercise);
        }

        return exerciseList;
    }

}
