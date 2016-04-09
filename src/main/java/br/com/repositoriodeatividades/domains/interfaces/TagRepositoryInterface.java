package br.com.repositoriodeatividades.domains.interfaces;

import br.com.repositoriodeatividades.entities.Tag;

import java.util.Collection;

public interface TagRepositoryInterface extends GenericRepositoryInterface<Tag>{

    public Collection<Tag> list();
    public Collection<Tag> find(Tag tag);
}
