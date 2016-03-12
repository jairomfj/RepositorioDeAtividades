package br.com.repositoriodeatividades.domains.dao.exercise;

import br.com.repositoriodeatividades.entities.Exercise;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class ExerciseDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(Exercise exercise) {
        entityManager.persist(exercise);
    }

}