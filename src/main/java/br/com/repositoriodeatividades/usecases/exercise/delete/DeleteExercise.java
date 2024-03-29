package br.com.repositoriodeatividades.usecases.exercise.delete;

import br.com.repositoriodeatividades.entities.ExerciseEntity;
import br.com.repositoriodeatividades.entities.UserEntity;
import br.com.repositoriodeatividades.repositories.interfaces.ExerciseRepositoryInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DeleteExercise {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ExerciseRepositoryInterface exerciseRepository;

    public void delete(UserEntity userEntity, UUID externalId) {
        if (externalId == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }

        ExerciseEntity exercise = exerciseRepository.findBy(userEntity, externalId);
        exerciseRepository.delete(exercise);
        log.info("Exercise deleted successfully");
    }


}
