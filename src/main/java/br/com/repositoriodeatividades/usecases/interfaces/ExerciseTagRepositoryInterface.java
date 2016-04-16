package br.com.repositoriodeatividades.usecases.interfaces;

import br.com.repositoriodeatividades.entities.ExerciseTag;

import java.util.Collection;

public interface ExerciseTagRepositoryInterface extends GenericRepositoryInterface<ExerciseTag>{

    public Collection<ExerciseTag> list();
}
