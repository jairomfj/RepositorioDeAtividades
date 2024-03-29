package br.com.repositoriodeatividades.usecases.exercise.retrieve;

import br.com.repositoriodeatividades.entities.ExerciseEntity;
import br.com.repositoriodeatividades.entities.UserEntity;
import br.com.repositoriodeatividades.repositories.interfaces.ExerciseRepositoryInterface;
import br.com.repositoriodeatividades.usecases.exercise.retrieve.models.vo.ExerciseVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RetrieveSingleExercise {

    private final Logger log = LoggerFactory.getLogger(this.getClass());


    @Autowired
    RetrieveExercises retrieveExercises;

    @Autowired
    ExerciseRepositoryInterface exerciseRepository;


    public ExerciseVO retrieve(UserEntity user, UUID externalId) {
        ExerciseEntity exercise = retrieveBy(user, externalId);
        return new ExerciseVO(
                exercise.getId(),
                exercise.getLabel(),
                exercise.getType(),
                exercise.getLevel(),
                exercise.getUser(),
                exercise.getExerciseOptions(),
                exercise.getTags(),
                exercise.getOptions()
        );
    }

    public ExerciseEntity retrieveBy(UserEntity currentUser, UUID externalId) {
        try {
            return exerciseRepository.findBy(currentUser, externalId);
        } catch (EmptyResultDataAccessException erdae){
            log.info("Could not find exercise id: " + externalId + " for user id: " + currentUser.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
