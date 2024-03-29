package br.com.repositoriodeatividades.repositories.interfaces;

import br.com.repositoriodeatividades.entities.ExerciseOptionEntity;

public interface ExerciseOptionRepositoryInterface extends GenericRepositoryInterface<ExerciseOptionEntity> {

    ExerciseOptionEntity find(ExerciseOptionEntity exerciseOption);

}
