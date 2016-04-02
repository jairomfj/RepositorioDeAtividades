package br.com.repositoriodeatividades.domains.exercise.dao;

import br.com.repositoriodeatividades.entities.Exercise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class ExerciseDao {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @PersistenceContext
    private EntityManager entityManager;

    public void create(Exercise exercise) {
        log.info("Creating new exercise for user: " + exercise.getUser());
        entityManager.persist(exercise);
    }

}