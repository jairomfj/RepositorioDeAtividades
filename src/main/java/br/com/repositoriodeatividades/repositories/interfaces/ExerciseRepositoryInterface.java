package br.com.repositoriodeatividades.repositories.interfaces;

import br.com.repositoriodeatividades.entities.Exercise;
import br.com.repositoriodeatividades.usecases.activities.create.vo.CreateActivityParameters;

import java.util.List;

public interface ExerciseRepositoryInterface extends GenericRepositoryInterface<Exercise> {

    List findAllByActivityParameters(CreateActivityParameters createActivityParameters);

}
