package br.com.repositoriodeatividades.repositories;

import br.com.repositoriodeatividades.repositories.interfaces.TagRepositoryInterface;
import br.com.repositoriodeatividades.entities.Tag;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.Collection;

@Repository
public class TagRepositoryImplementation extends GenericRepositoryImplementation<Tag> implements TagRepositoryInterface {

    public TagRepositoryImplementation() {
        super(Tag.class);
    }

    @Override
    public Collection<Tag> list() {
        TypedQuery<Tag> query = entityManager.createQuery("select t from Tag t", Tag.class);
        return query.getResultList();
    }

    @Override
    public Collection<Tag> find(Tag tag) {
        return entityManager.createQuery(
                "select t from Tag t where t.label = :label", Tag.class)
                .setParameter("label", tag.getLabel())
                .getResultList();
    }



}