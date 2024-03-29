package br.com.repositoriodeatividades.usecases.exercise.imports.models;

import br.com.repositoriodeatividades.usecases.exercise.utils.interfaces.Parseable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Component("exerciseParser")
public class ExerciseParser implements Parseable {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private static final int THREAD_AMOUNT = 5;

    @Override
    public List<ExtractedExercise> parse(List<String> exercises) throws Exception {
        if (exercises == null || exercises.size() == 0) {
            throw new IllegalAccessException("List of exercises cannot be or empty");
        }
        return parseInParallel(exercises);
    }

    public List<ExtractedExercise> parseInParallel(List<String> exercises) {

        log.info("Execute parse in parallel");

        List<ExtractedExercise> exerciseList = new ArrayList<>();

        try {
            ExecutorService executor = Executors.newFixedThreadPool(THREAD_AMOUNT);
            for (final String exercise : exercises) {
                executor.execute(new ExerciseParserParallel(exercise, exerciseList));
            }
            executor.shutdown();
            while (!executor.awaitTermination(24L, TimeUnit.SECONDS)) {
            }
            log.info("Parse ended");
        } catch (InterruptedException e) {
            log.error("Error during parse: ", e);
            e.printStackTrace();

        }

        return exerciseList;
    }


}
