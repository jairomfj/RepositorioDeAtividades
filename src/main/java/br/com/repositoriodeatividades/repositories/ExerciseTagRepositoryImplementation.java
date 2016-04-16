package br.com.repositoriodeatividades.repositories;

import br.com.repositoriodeatividades.usecases.interfaces.ExerciseTagRepositoryInterface;
import br.com.repositoriodeatividades.entities.ExerciseTag;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.Collection;


@Repository
public class ExerciseTagRepositoryImplementation extends GenericRepositoryImplementation<ExerciseTag> implements ExerciseTagRepositoryInterface {

    public ExerciseTagRepositoryImplementation() {
        super(ExerciseTag.class);
    }

    @Override
    public Collection<ExerciseTag> list() {
        TypedQuery<ExerciseTag> query = entityManager.createQuery("select t from ExerciseTag t", ExerciseTag.class);
        return query.getResultList();
    }
}
