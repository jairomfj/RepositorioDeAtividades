package br.com.repositoriodeatividades.usecases.activities.create;

import br.com.repositoriodeatividades.entities.ExerciseEntity;
import br.com.repositoriodeatividades.entities.ExerciseOptionEntity;
import br.com.repositoriodeatividades.entities.UserEntity;
import br.com.repositoriodeatividades.repositories.interfaces.ExerciseRepositoryInterface;
import br.com.repositoriodeatividades.repositories.interfaces.UserRepositoryInterface;
import br.com.repositoriodeatividades.usecases.activities.create.models.ExerciseClassifier;
import br.com.repositoriodeatividades.usecases.activities.create.models.vo.CreateActivityParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CreateActivity {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ExerciseRepositoryInterface exerciseRepository;

    @Autowired
    UserRepositoryInterface userRepository;

    @Autowired
    ExerciseClassifier exerciseClassifier;

    public List<ExerciseEntity> execute(List<CreateActivityParameters> createActivityParametersList) {

        if(createActivityParametersList.isEmpty()) {
            throw new IllegalArgumentException("No data has been passed");
        }

        UserEntity user = userRepository.findByUsername(createActivityParametersList.get(0).getUsername());
        if(user == null) {
            throw new IllegalStateException("User not found");
        }

        List<ExerciseEntity> finalExerciseList = new ArrayList<>();
        for(CreateActivityParameters createActivityParameters : createActivityParametersList) {

            createActivityParameters.setUser(user);

            try {
                List persistedExercises = exerciseRepository.findAllBy(createActivityParameters);
                List<Map> exercisesClassified = exerciseClassifier.classify(persistedExercises, createActivityParameters.getTags());

                int totalAdded = 0;
                for(int i = 0; totalAdded < createActivityParameters.getAmount() &&  i < exercisesClassified.size(); i++) {
                    Map orderedExercise = exercisesClassified.get(i);
                    if(!shouldAddExerciseToFinalList((ExerciseEntity) orderedExercise.get("exercise"), finalExerciseList) &&
                            isScoreGraterThan(10.0, (Double) orderedExercise.get("score"))) {
                        finalExerciseList.add((ExerciseEntity) orderedExercise.get("exercise"));
                        totalAdded++;
                    }
                }

            } catch (Exception e) {
                log.error("Error while extracting exercises");
            }
        }

        long seed = System.nanoTime();
        Collections.shuffle(finalExerciseList, new Random(seed));

        enumerateExercises(finalExerciseList);

        return finalExerciseList;
    }

    private boolean isScoreGraterThan(double min, Double score) {
        return score * 100 >  min;
    }

    private boolean shouldAddExerciseToFinalList(ExerciseEntity orderedExercise, List<ExerciseEntity> finalExerciseList) {
        boolean alreadyAdded = false;
        for(ExerciseEntity finalExercise : finalExerciseList) {
            if(orderedExercise.getId() == finalExercise.getId())
                alreadyAdded = true;
        }
        return alreadyAdded;
    }

    private void enumerateExercises(List<ExerciseEntity> exerciseList) {
        for(int i = 0; i < exerciseList.size(); i++) {
            ExerciseEntity exercise = exerciseList.get(i);
            String exerciseLabel = i + 1 + ") " + exercise.getLabel();
            exercise.setLabel(exerciseLabel);
            enumerateExerciseOptions(exercise.getExerciseOptions());
        }
    }

    private void enumerateExerciseOptions(List<ExerciseOptionEntity> exerciseOptionList) {
        char enumeration = 'a';
        for(ExerciseOptionEntity option : exerciseOptionList) {
            String optionLabel = enumeration + ") " + option.getLabel();
            option.setLabel(optionLabel);
            enumeration++;
        }
    }

}
