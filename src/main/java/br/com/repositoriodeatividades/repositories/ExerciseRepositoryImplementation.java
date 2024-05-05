package br.com.repositoriodeatividades.repositories;

import br.com.repositoriodeatividades.entities.ExerciseEntity;
import br.com.repositoriodeatividades.entities.UserEntity;
import br.com.repositoriodeatividades.repositories.interfaces.ExerciseRepositoryInterface;
import br.com.repositoriodeatividades.usecases.activities.create.models.vo.CreateActivityParameters;
import br.com.repositoriodeatividades.usecases.exercise.utils.enums.ExerciseLevel;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class ExerciseRepositoryImplementation extends GenericRepositoryImplementation<ExerciseEntity> implements ExerciseRepositoryInterface {

    public ExerciseRepositoryImplementation() {
        super(ExerciseEntity.class);
    }

    @Override
    public List findAllBy(CreateActivityParameters activityParameters) {
            return entityManager.createQuery(
                    "select e from Exercise e " +
                    "where e.level = :level and e.user = :user")
                    .setParameter("level", ExerciseLevel.valueOf(activityParameters.getLevel()))
                    .setParameter("user", activityParameters.getUser())
                    .getResultList();

    }

    @Override
    public List<ExerciseEntity> findAllBy(UserEntity user) {
        return entityManager.createQuery(
                "select e from Exercise e " +
                "where e.user = :user", ExerciseEntity.class)
                .setParameter("user", user)
                .getResultList();
    }

    @Override
    public ExerciseEntity findBy(UserEntity user, Long id) {
        return entityManager.createQuery(
                "select e from Exercise e " +
                "where e.user = :user and e.id = :id", ExerciseEntity.class)
                .setParameter("user", user)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public ExerciseEntity findBy(UserEntity user, UUID externalId) {
        return entityManager.createQuery(
                "select e from Exercise e " +
                        "where e.user = :user and e.externalId = :externalId", ExerciseEntity.class)
                .setParameter("user", user)
                .setParameter("externalId", externalId.toString())
                .getSingleResult();
    }
}
