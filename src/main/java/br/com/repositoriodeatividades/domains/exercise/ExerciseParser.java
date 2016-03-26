package br.com.repositoriodeatividades.domains.exercise;

import br.com.repositoriodeatividades.domains.interfaces.ExerciseItem;
import br.com.repositoriodeatividades.domains.interfaces.Parseable;
import br.com.repositoriodeatividades.domains.vo.exercise.MultipleChoiceExerciseItem;
import br.com.repositoriodeatividades.entities.Exercise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Component("exerciseParser")
public class ExerciseParser implements Parseable {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final int THREAD_AMMOUNT = 5;

    @Override
    public List<Exercise> parse(List<String> exercises) throws Exception {
        if(exercises == null || exercises.size() == 0) {
            throw new IllegalAccessException("List of exercises cannot be or empty");
        }
        return parseInParallel(exercises);
    }

    public List<Exercise> parseInParallel(List<String> exercises) throws Exception {

        log.info("Execute parse in parallel");

        List<Exercise> exerciseList = new ArrayList();

        try {
            ExecutorService executor = Executors.newFixedThreadPool(THREAD_AMMOUNT);
            for(final String exercise : exercises) {
               executor.execute(new ExerciseParserParallel(exercise, exerciseList));
            }
            executor.shutdown();
            while (!executor.awaitTermination(24L, TimeUnit.SECONDS)) { }
            log.info("Parse ended");
        } catch (InterruptedException e) {
            log.error("Error during parse: " + e.getStackTrace().toString());
            e.printStackTrace();

        }
        return exerciseList;
    }


}
