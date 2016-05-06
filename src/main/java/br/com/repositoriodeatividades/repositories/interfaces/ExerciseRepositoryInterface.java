package br.com.repositoriodeatividades.repositories.interfaces;

import br.com.repositoriodeatividades.entities.Exercise;
import br.com.repositoriodeatividades.entities.User;
import br.com.repositoriodeatividades.usecases.activities.create.vo.CreateActivityParameters;

import java.util.List;

public interface ExerciseRepositoryInterface extends GenericRepositoryInterface<Exercise> {

    List findAllBy(CreateActivityParameters createActivityParameters);
    List<Exercise> findAllBy(User user);
    Exercise findBy(User user, Long id);

}
