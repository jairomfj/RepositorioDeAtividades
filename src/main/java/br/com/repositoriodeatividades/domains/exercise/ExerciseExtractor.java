package br.com.repositoriodeatividades.domains.exercise;

import br.com.repositoriodeatividades.domains.enums.ExerciseEnumeration;
import br.com.repositoriodeatividades.domains.interfaces.Extractable;
import br.com.repositoriodeatividades.domains.util.RepositoryUtils;
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

        List<String> exercises = new ArrayList<String>();
        String exercise = "";
        ExerciseEnumeration candidateExerciseEnumeration = null;
        String[] splitFileContent = fileContent.split("\\n");

        for(String line : splitFileContent) {
            ExerciseEnumeration exerciseEnumeration = repositoryUtils.findEnumeration(line.trim());

            if (candidateExerciseEnumeration == null) {
                candidateExerciseEnumeration = exerciseEnumeration;
            } else if(exerciseEnumeration == candidateExerciseEnumeration) {
                log.info("Exercise text: \n" + exercise);
                exercises.add(exercise);
                exercise = "";
            }
            exercise += line.trim() + "\n";
        }

        if(exercise.equals("")) {
            throw new IllegalArgumentException("Exercise empty");
        }

        exercises.add(exercise);
        return exercises;
    }
}
