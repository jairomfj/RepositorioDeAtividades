package br.com.repositoriodeatividades.usecases.exercise.retrieve.models;

import br.com.repositoriodeatividades.entities.Exercise;
import br.com.repositoriodeatividades.entities.User;
import br.com.repositoriodeatividades.repositories.interfaces.ExerciseRepositoryInterface;
import br.com.repositoriodeatividades.usecases.exercise.retrieve.RetrieveExercises;
import br.com.repositoriodeatividades.usecases.exercise.retrieve.models.vo.ExerciseVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

@Component
public class RetrieveSingleExercise {

    private final Logger log = LoggerFactory.getLogger(this.getClass());


    @Autowired
    RetrieveExercises retrieveExercises;

    @Autowired
    ExerciseRepositoryInterface exerciseRepository;


    public ExerciseVO retrieve(User user, Long exerciseId) {
        Exercise exercise = retrieveBy(user, exerciseId);
        return new ExerciseVO(
                exercise.getId(),
                exercise.getLabel(),
                exercise.getType(),
                exercise.getLevel(),
                exercise.getUser(),
                exercise.getExerciseOptions(),
                exercise.getTags()
        );
    }

    public Exercise retrieveBy(User currentUser, Long id) {
        try {
            return exerciseRepository.findBy(currentUser, id);
        } catch (EmptyResultDataAccessException erdae){
            log.info("Could not find exercise id: " + id + " for user id: " + currentUser.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
