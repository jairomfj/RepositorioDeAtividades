package br.com.repositoriodeatividades.domains.dao;

import br.com.repositoriodeatividades.entities.Exercise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class AbstractDao {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @PersistenceContext
    private EntityManager entityManager;

    public void create(Object object) {
        entityManager.persist(object);
    }

}