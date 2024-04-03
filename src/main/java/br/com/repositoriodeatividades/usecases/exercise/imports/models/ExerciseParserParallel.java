package br.com.repositoriodeatividades.usecases.exercise.imports.models;

import br.com.repositoriodeatividades.entities.ExerciseOptionEntity;
import br.com.repositoriodeatividades.usecases.exercise.utils.ExerciseBuilder;
import br.com.repositoriodeatividades.usecases.exercise.utils.interfaces.ExerciseItem;
import br.com.repositoriodeatividades.entities.ExerciseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class ExerciseParserParallel implements Runnable {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private String exerciseString;

    private List<ExtractedExercise> exerciseList;
    private ExerciseBuilder exerciseBuilder;
    private ExerciseItemBuilder exerciseItemBuilder;

    ExerciseParserParallel(String exerciseString, List<ExtractedExercise> exerciseList) {
        this.exerciseString = exerciseString;
        this.exerciseList = exerciseList;
        this.exerciseBuilder = new ExerciseBuilder();
        this.exerciseItemBuilder = new ExerciseItemBuilder(exerciseString);
    }

    @Override
    public void run() {
        try {
            log.info("Running thread");
            List<ExerciseItem> exerciseItemList = exerciseItemBuilder.buildExerciseItems();
            ExerciseEntity exercise = exerciseBuilder.build(exerciseItemList);
            String[] list = exercise.getExerciseOptions().stream().map((ExerciseOptionEntity::getLabel)).toList().toArray(new String[0]);

            exerciseList.add(new ExtractedExercise(
                    exercise.getLabel(),
                    exercise.getType(),
                    exercise.getOptions(),
                    this.exerciseString,
                    list

            ));

        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

}
