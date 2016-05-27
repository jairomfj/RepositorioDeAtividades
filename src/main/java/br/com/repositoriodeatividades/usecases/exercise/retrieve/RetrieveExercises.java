package br.com.repositoriodeatividades.usecases.exercise.retrieve;

import br.com.repositoriodeatividades.entities.Exercise;
import br.com.repositoriodeatividades.entities.User;
import br.com.repositoriodeatividades.repositories.interfaces.ExerciseRepositoryInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RetrieveExercises {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ExerciseRepositoryInterface exerciseRepository;

    public List<Exercise> findAllBy(User currentUser) {
        return exerciseRepository.findAllBy(currentUser);
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
