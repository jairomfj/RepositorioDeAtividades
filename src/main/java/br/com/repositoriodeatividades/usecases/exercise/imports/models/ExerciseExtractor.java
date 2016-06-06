package br.com.repositoriodeatividades.usecases.exercise.imports.models;

import br.com.repositoriodeatividades.usecases.exercise.utils.enums.ExerciseEnumeration;
import br.com.repositoriodeatividades.usecases.exercise.utils.interfaces.Extractable;
import br.com.repositoriodeatividades.usecases.util.RepositoryUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class ExerciseExtractor implements Extractable {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public RepositoryUtils repositoryUtils;

    @Override
    public List<String> extract(String fileContent) throws Exception {

        log.info("Extracting list of exercises as string");

        if(fileContent == null || fileContent.equals("")) {
            throw new IllegalArgumentException("File cannot be empty");
        }

        List<String> exercises = new ArrayList<>();
        ExerciseEnumeration candidateExerciseEnumeration = null;
        String[] splitFileContent = fileContent.split("\\n");

        String exercise = "";
        for(String line : splitFileContent) {
            String[] splittedFinalLine = line.split("\\r");
            for (String finalLine : splittedFinalLine) {
                ExerciseEnumeration exerciseEnumeration = repositoryUtils.findEnumeration(finalLine.trim());

                if (candidateExerciseEnumeration == null && !exerciseEnumeration.equals(ExerciseEnumeration.NONE)) {
                    candidateExerciseEnumeration = exerciseEnumeration;
                } else if (isNewExercise(candidateExerciseEnumeration, exerciseEnumeration)) {
                    log.info("Exercise text: " + exercise);
                    exercises.add(exercise);
                    exercise = "";
                }
                exercise += finalLine.trim() + "\n";
            }
        }

        if(exercise.equals("")) {
            throw new IllegalArgumentException("Exercise empty");
        }

        exercises.add(exercise);
        return exercises;
    }

    private boolean isNewExercise(ExerciseEnumeration candidateExerciseEnumeration, ExerciseEnumeration exerciseEnumeration) {
        return exerciseEnumeration == candidateExerciseEnumeration && exerciseEnumeration != ExerciseEnumeration.NONE;
    }
}
