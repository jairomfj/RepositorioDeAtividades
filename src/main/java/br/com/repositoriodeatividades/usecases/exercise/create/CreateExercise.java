package br.com.repositoriodeatividades.usecases.exercise.create;


import br.com.repositoriodeatividades.entities.Exercise;
import br.com.repositoriodeatividades.entities.ExerciseOption;
import br.com.repositoriodeatividades.entities.Tag;
import br.com.repositoriodeatividades.entities.User;
import br.com.repositoriodeatividades.repositories.interfaces.ExerciseOptionRepositoryInterface;
import br.com.repositoriodeatividades.repositories.interfaces.ExerciseRepositoryInterface;
import br.com.repositoriodeatividades.repositories.interfaces.TagRepositoryInterface;
import br.com.repositoriodeatividades.repositories.interfaces.UserRepositoryInterface;
import br.com.repositoriodeatividades.usecases.exercise.create.models.TagParser;
import br.com.repositoriodeatividades.usecases.exercise.utils.ExerciseBuilder;
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

    public void saveFileExtractedExercises(ExercisePlain exercisePlain) throws IllegalAccessException {

        log.info("Saving exercise extracted from file");

        if(exercisePlain.equals(null)) {
            throw new IllegalArgumentException("Exercise label cannot be null");
        }

        User user = userRepository.findByUsername(exercisePlain.getUsername());
        if(user == null) {
            throw new IllegalStateException("User not found");
        }

        Exercise exercise = exerciseBuilder.build(exercisePlain);
        exercise.setUser(user);
        exerciseRepository.save(exercise);

        List<Tag> tagList = tagParser.parse(exercisePlain.getExerciseTags(), exercise);
        for(Tag tag : tagList)
            tagRepository.save(tag);

        for(ExerciseOption exerciseOption : exercise.getExerciseOptions())
            exerciseOptionRepository.save(exerciseOption);

    }
}
