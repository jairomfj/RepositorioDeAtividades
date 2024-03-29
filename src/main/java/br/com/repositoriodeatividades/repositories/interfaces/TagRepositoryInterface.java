package br.com.repositoriodeatividades.repositories.interfaces;

import br.com.repositoriodeatividades.entities.TagEntity;

import java.util.Collection;

public interface TagRepositoryInterface extends GenericRepositoryInterface<TagEntity>{

    Collection<TagEntity> list();
    Collection<TagEntity> find(TagEntity tag);
}
