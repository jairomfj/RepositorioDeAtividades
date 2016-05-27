package br.com.repositoriodeatividades.usecases.exercise.delete;

import br.com.repositoriodeatividades.entities.Exercise;
import br.com.repositoriodeatividades.repositories.interfaces.ExerciseRepositoryInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteExercise {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ExerciseRepositoryInterface exerciseRepository;


    public void delete(Long id) {

        if(id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }

        Exercise exercise = exerciseRepository.find(id);
        exerciseRepository.delete(exercise);
        log.info("Exercise deleted successfully");
    }


}
