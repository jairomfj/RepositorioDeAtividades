package br.com.repositoriodeatividades.repositories;

import br.com.repositoriodeatividades.entities.ExerciseOptionEntity;
import br.com.repositoriodeatividades.repositories.interfaces.ExerciseOptionRepositoryInterface;
import org.springframework.stereotype.Repository;

@Repository
public class ExerciseOptionRepositoryImplementation extends GenericRepositoryImplementation<ExerciseOptionEntity> implements ExerciseOptionRepositoryInterface {

    public ExerciseOptionRepositoryImplementation() {
        super(ExerciseOptionEntity.class);
    }

    @Override
    public ExerciseOptionEntity find(ExerciseOptionEntity exerciseOption) {
        return entityManager.createQuery(
                "select e from ExerciseOption e where e.label = :label and e.exercise = :exercise", ExerciseOptionEntity.class)
                .setParameter("label", exerciseOption.getLabel())
                .setParameter("exercise", exerciseOption.getExercise())
                .getSingleResult();
    }
}
