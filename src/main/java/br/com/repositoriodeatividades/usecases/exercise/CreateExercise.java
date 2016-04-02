package br.com.repositoriodeatividades.usecases.exercise;


import br.com.repositoriodeatividades.domains.exercise.dao.ExerciseDao;
import br.com.repositoriodeatividades.domains.exercise.vo.ExercisePlain;
import br.com.repositoriodeatividades.entities.Exercise;
import br.com.repositoriodeatividades.entities.ExerciseOption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CreateExercise {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ExerciseDao exerciseDao;

    public void saveFileExtractedExercises(ExercisePlain exercisePlain, String[] optionLabels) throws IllegalAccessException {

        log.info("Saving exercise extracted from file");

        if(exercisePlain.equals(null)) {
            throw new IllegalArgumentException("Exercise label cannot be null");
        }

        Exercise exercise = parseExercise(exercisePlain, optionLabels);
        persist(exercise);
    }

    private Exercise parseExercise(ExercisePlain exercisePlain, String[] optionLabels) {
        List<ExerciseOption> exerciseOptionList = new ArrayList<ExerciseOption>();
        if(optionLabels.length > 0) {
            log.info("Exercise has " + optionLabels.length + " options");

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


    private void persist(Exercise exercise) {
        exerciseDao.create(exercise);
    }
}
