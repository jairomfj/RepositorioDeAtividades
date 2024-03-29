package br.com.repositoriodeatividades.usecases.exercise.create;


import br.com.repositoriodeatividades.entities.ExerciseEntity;
import br.com.repositoriodeatividades.entities.ExerciseOptionEntity;
import br.com.repositoriodeatividades.entities.TagEntity;
import br.com.repositoriodeatividades.entities.UserEntity;
import br.com.repositoriodeatividades.repositories.interfaces.ExerciseOptionRepositoryInterface;
import br.com.repositoriodeatividades.repositories.interfaces.ExerciseRepositoryInterface;
import br.com.repositoriodeatividades.repositories.interfaces.TagRepositoryInterface;
import br.com.repositoriodeatividades.repositories.interfaces.UserRepositoryInterface;
import br.com.repositoriodeatividades.usecases.exercise.create.models.TagParser;
import br.com.repositoriodeatividades.usecases.exercise.utils.ExerciseBuilder;
import br.com.repositoriodeatividades.usecases.exercise.utils.vo.CreateExerciseInput;
import br.com.repositoriodeatividades.usecases.exercise.utils.vo.ExercisePlain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor=Exception.class)
public class CreateExercise {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ExerciseRepositoryInterface exerciseRepository;

    @Autowired
    TagRepositoryInterface tagRepository;

    @Autowired
    ExerciseOptionRepositoryInterface exerciseOptionRepository;

    @Autowired
    UserRepositoryInterface userRepository;

    @Autowired
    TagParser tagParser;

    @Autowired
    ExerciseBuilder exerciseBuilder;

    public void saveExtractedExercise(CreateExerciseInput createExerciseInput) {
        log.info("Saving exercise extracted from file");

        if(createExerciseInput == null) {
            throw new IllegalArgumentException("Exercise label cannot be null");
        }

        UserEntity user = userRepository.find(createExerciseInput.getUserID());
        if(user == null) {
            throw new IllegalStateException("User not found");
        }

        ExerciseEntity exercise = exerciseBuilder.build(createExerciseInput);
        exercise.setOptions(createExerciseInput.getExerciseOptions());
        exercise.setUser(user);
        exerciseRepository.save(exercise);

        List<TagEntity> tagList = tagParser.parse(createExerciseInput.getExerciseTags(), exercise);
        for(TagEntity tag : tagList)
            tagRepository.save(tag);

        for(ExerciseOptionEntity exerciseOption : exercise.getExerciseOptions())
            exerciseOptionRepository.save(exerciseOption);

    }
}
