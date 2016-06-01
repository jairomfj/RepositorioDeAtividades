package br.com.repositoriodeatividades.repositories.interfaces;

import br.com.repositoriodeatividades.entities.Tag;

import java.util.Collection;

public interface TagRepositoryInterface extends GenericRepositoryInterface<Tag>{

    Collection<Tag> list();
    Collection<Tag> find(Tag tag);
}
