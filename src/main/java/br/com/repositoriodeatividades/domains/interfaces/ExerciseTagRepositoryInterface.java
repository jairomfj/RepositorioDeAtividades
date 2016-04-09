package br.com.repositoriodeatividades.domains.interfaces;

import br.com.repositoriodeatividades.entities.ExerciseTag;

import java.util.Collection;

public interface ExerciseTagRepositoryInterface extends GenericRepositoryInterface<ExerciseTag>{

    public Collection<ExerciseTag> list();
}
