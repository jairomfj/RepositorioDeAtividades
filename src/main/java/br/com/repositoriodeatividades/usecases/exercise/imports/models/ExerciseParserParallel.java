package br.com.repositoriodeatividades.usecases.exercise.imports.models;

import br.com.repositoriodeatividades.usecases.exercise.utils.ExerciseBuilder;
import br.com.repositoriodeatividades.usecases.exercise.utils.interfaces.ExerciseItem;
import br.com.repositoriodeatividades.entities.Exercise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ExerciseParserParallel implements Runnable {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private List<Exercise> exerciseList;
    ExerciseBuilder exerciseBuilder;
    ExerciseItemBuilder exerciseItemBuilder;

    ExerciseParserParallel(String exerciseString, List<Exercise> exerciseList) {
        this.exerciseList = exerciseList;
        this.exerciseBuilder = new ExerciseBuilder();
        this.exerciseItemBuilder = new ExerciseItemBuilder(exerciseString);
    }

    @Override
    public void run() {
        try {
            log.info("Running thread");
            List<ExerciseItem> exerciseItemList = exerciseItemBuilder.buildExerciseItems();
            Exercise exercise = exerciseBuilder.build(exerciseItemList);
            exerciseList.add(exercise);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

}
