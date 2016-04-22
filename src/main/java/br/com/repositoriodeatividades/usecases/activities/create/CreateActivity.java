package br.com.repositoriodeatividades.usecases.activities.create;

import br.com.repositoriodeatividades.entities.Exercise;
import br.com.repositoriodeatividades.repositories.interfaces.ExerciseRepositoryInterface;
import br.com.repositoriodeatividades.usecases.activities.create.vo.CreateActivityParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CreateActivity {

    @Autowired
    ExerciseRepositoryInterface exerciseRepository;

    @Autowired
    ExerciseClassifier exerciseClassifier;

    public List<Exercise> execute(List<CreateActivityParameters> createActivityParametersList) throws Exception {

        if(createActivityParametersList.size() == 0) {
            throw new IllegalArgumentException("No data has been passed");
        }

        List<Exercise> finalExerciseList = new ArrayList<>();
        for(CreateActivityParameters createActivityParameters : createActivityParametersList) {
            try {
                List persistedExercises = exerciseRepository.findAllByActivityParameters(createActivityParameters);
                List<Map> exercisesClassified = exerciseClassifier.classify(persistedExercises, createActivityParameters.getTags());
                finalExerciseList.addAll(getValuesFromListLimitedBy(createActivityParameters.getAmmount(), exercisesClassified));
            } catch (Exception e) {

            }
        }

        long seed = System.nanoTime();
        Collections.shuffle(finalExerciseList, new Random(seed));

        return finalExerciseList;
    }

    private List<Exercise> getValuesFromListLimitedBy(int ammount, List<Map> exercises) {
        List<Exercise> exerciseList = new ArrayList<>();
        for(int i = 0; i < ammount; i++) {
            exerciseList.add((Exercise) exercises.get(i).get("exercise"));
        }
        return exerciseList;
    }


}
