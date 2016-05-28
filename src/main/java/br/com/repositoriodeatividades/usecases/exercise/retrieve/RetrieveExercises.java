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


    @Autowired
    ExerciseRepositoryInterface exerciseRepository;

    public List<Exercise> findAllBy(User currentUser) {
        return exerciseRepository.findAllBy(currentUser);
    }


}
