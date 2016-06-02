package br.com.repositoriodeatividades.repositories.interfaces;

import br.com.repositoriodeatividades.entities.ExerciseOption;

public interface ExerciseOptionRepositoryInterface extends GenericRepositoryInterface<ExerciseOption> {

    ExerciseOption find(ExerciseOption exerciseOption);

}
