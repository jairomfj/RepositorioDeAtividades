package br.com.repositoriodeatividades.domains.repositories;

import br.com.repositoriodeatividades.domains.interfaces.ExerciseRepositoryInterface;
import br.com.repositoriodeatividades.entities.Exercise;
import org.springframework.stereotype.Repository;

@Repository
public class ExerciseRepositoryImplementation extends GenericRepositoryImplementation<Exercise> implements ExerciseRepositoryInterface {

    public ExerciseRepositoryImplementation() {
        super(Exercise.class);
    }

}
