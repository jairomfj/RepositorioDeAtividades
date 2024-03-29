package br.com.repositoriodeatividades.repositories;

import br.com.repositoriodeatividades.repositories.interfaces.TagRepositoryInterface;
import br.com.repositoriodeatividades.entities.TagEntity;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class TagRepositoryImplementation extends GenericRepositoryImplementation<TagEntity> implements TagRepositoryInterface {

    public TagRepositoryImplementation() {
        super(TagEntity.class);
    }

    @Override
    public Collection<TagEntity> list() {
        TypedQuery<TagEntity> query = entityManager.createQuery("select t from Tag t", TagEntity.class);
        return query.getResultList();
    }

    @Override
    public Collection<TagEntity> find(TagEntity tag) {
        return entityManager.createQuery(
                "select t from Tag t where t.label = :label and t.exercise = :exercise", TagEntity.class)
                .setParameter("label", tag.getLabel())
                .setParameter("exercise", tag.getExercise())
                .getResultList();
    }



}