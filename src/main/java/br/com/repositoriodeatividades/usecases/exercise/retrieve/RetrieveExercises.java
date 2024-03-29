package br.com.repositoriodeatividades.usecases.exercise.retrieve;

import br.com.repositoriodeatividades.entities.ExerciseEntity;
import br.com.repositoriodeatividades.entities.UserEntity;
import br.com.repositoriodeatividades.repositories.interfaces.ExerciseRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RetrieveExercises {


    @Autowired
    ExerciseRepositoryInterface exerciseRepository;

    public List<ExerciseEntity> findAllBy(UserEntity currentUser) {
        return exerciseRepository.findAllBy(currentUser);
    }


}
