package br.com.repositoriodeatividades.repositories.interfaces;

import br.com.repositoriodeatividades.entities.ExerciseEntity;
import br.com.repositoriodeatividades.entities.UserEntity;
import br.com.repositoriodeatividades.usecases.activities.create.models.vo.CreateActivityParameters;

import java.util.List;
import java.util.UUID;

public interface ExerciseRepositoryInterface extends GenericRepositoryInterface<ExerciseEntity> {

    List findAllBy(CreateActivityParameters createActivityParameters);
    List<ExerciseEntity> findAllBy(UserEntity user);
    ExerciseEntity findBy(UserEntity user, Long id);
    ExerciseEntity findBy(UserEntity user, UUID externalId);

}
