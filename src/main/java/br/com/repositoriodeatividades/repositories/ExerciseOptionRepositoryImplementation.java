package br.com.repositoriodeatividades.repositories;

import br.com.repositoriodeatividades.entities.ExerciseOption;
import br.com.repositoriodeatividades.repositories.interfaces.ExerciseOptionRepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ExerciseOptionRepositoryImplementation extends GenericRepositoryImplementation<ExerciseOption> implements ExerciseOptionRepositoryInterface {

    public ExerciseOptionRepositoryImplementation() {
        super(ExerciseOption.class);
    }

    @Override
    public ExerciseOption find(ExerciseOption exerciseOption) {
        return entityManager.createQuery(
                "select e from ExerciseOption e where e.label = :label and e.exercise = :exercise", ExerciseOption.class)
                .setParameter("label", exerciseOption.getLabel())
                .setParameter("exercise", exerciseOption.getExercise())
                .getSingleResult();
    }
}
