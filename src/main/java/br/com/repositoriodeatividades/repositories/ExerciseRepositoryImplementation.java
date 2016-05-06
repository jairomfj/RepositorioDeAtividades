package br.com.repositoriodeatividades.repositories;

import br.com.repositoriodeatividades.entities.Exercise;
import br.com.repositoriodeatividades.entities.User;
import br.com.repositoriodeatividades.repositories.interfaces.ExerciseRepositoryInterface;
import br.com.repositoriodeatividades.usecases.activities.create.vo.CreateActivityParameters;
import br.com.repositoriodeatividades.usecases.exercise.enums.ExerciseLevelType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ExerciseRepositoryImplementation extends GenericRepositoryImplementation<Exercise> implements ExerciseRepositoryInterface {

    public ExerciseRepositoryImplementation() {
        super(Exercise.class);
    }

    @Override
    public List findAllBy(CreateActivityParameters activityParameters) {
            return entityManager.createQuery(
                    "select e from Exercise e " +
                    "where e.level = :level and e.user = :user")
                    .setParameter("level", ExerciseLevelType.valueOf(activityParameters.getLevel()))
                    .setParameter("user", activityParameters.getUser())
                    .getResultList();

    }

    @Override
    public List<Exercise> findAllBy(User user) {
        return entityManager.createQuery(
                "select e from Exercise e " +
                "where e.user = :user", Exercise.class)
                .setParameter("user", user)
                .getResultList();
    }

    @Override
    public Exercise findBy(User user, Long id) {
        return entityManager.createQuery(
                "select e from Exercise e " +
                "where e.user = :user and e.id = :id", Exercise.class)
                .setParameter("user", user)
                .setParameter("id", id)
                .getSingleResult();
    }
}
