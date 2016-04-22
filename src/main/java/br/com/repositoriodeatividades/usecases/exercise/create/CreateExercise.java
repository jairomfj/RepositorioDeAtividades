package br.com.repositoriodeatividades.usecases.exercise.create;


import br.com.repositoriodeatividades.usecases.exercise.create.builders.ExerciseTagBuilder;
import br.com.repositoriodeatividades.repositories.interfaces.ExerciseRepositoryInterface;
import br.com.repositoriodeatividades.repositories.interfaces.ExerciseTagRepositoryInterface;
import br.com.repositoriodeatividades.repositories.interfaces.TagRepositoryInterface;
import br.com.repositoriodeatividades.usecases.exercise.ExerciseBuilder;
import br.com.repositoriodeatividades.usecases.vo.ExercisePlain;
import br.com.repositoriodeatividades.entities.Exercise;
import br.com.repositoriodeatividades.entities.ExerciseTag;
import br.com.repositoriodeatividades.entities.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackFor=Exception.class)
public class CreateExercise {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ExerciseRepositoryInterface exerciseRepositoryImplementation;

    @Autowired
    ExerciseTagRepositoryInterface exerciseTagRepositoryImplementation;

    @Autowired
    TagRepositoryInterface tagRepositoryImplementation;

    @Autowired
    TagParser tagParser;

    @Autowired
    ExerciseTagBuilder exerciseTagBuilder;

    @Autowired
    ExerciseBuilder exerciseBuilder;

    public void saveFileExtractedExercises(ExercisePlain exercisePlain, String[] optionLabels, String[] tags) throws IllegalAccessException {

        log.info("Saving exercise extracted from file");

        if(exercisePlain.equals(null)) {
            throw new IllegalArgumentException("Exercise label cannot be null");
        }

        Exercise exercise = exerciseBuilder.build(exercisePlain, optionLabels);
        exerciseRepositoryImplementation.save(exercise);

        List<Tag> tagList = tagParser.parse(tags, exercise);
        for(Tag tag : tagList) {
            tagRepositoryImplementation.save(tag);
        }

    }

    private void persistExerciseTags(List<ExerciseTag> exerciseTagList) {
        if(exerciseTagList != null && exerciseTagList.size() > 0) {
            for(ExerciseTag exerciseTag : exerciseTagList) {
                exerciseTagRepositoryImplementation.save(exerciseTag);
            }
        }
    }
}
