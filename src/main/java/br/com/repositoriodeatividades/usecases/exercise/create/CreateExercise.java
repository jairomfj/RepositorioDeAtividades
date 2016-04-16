package br.com.repositoriodeatividades.usecases.exercise.create;


import br.com.repositoriodeatividades.usecases.interfaces.ExerciseRepositoryInterface;
import br.com.repositoriodeatividades.usecases.interfaces.ExerciseTagRepositoryInterface;
import br.com.repositoriodeatividades.usecases.interfaces.TagRepositoryInterface;
import br.com.repositoriodeatividades.usecases.exercise.ExerciseBuilder;
import br.com.repositoriodeatividades.usecases.vo.ExercisePlain;
import br.com.repositoriodeatividades.usecases.vo.ExerciseStructure;
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
        List<Tag> tagList = parseTags(tags);
        persistData(new ExerciseStructure(exercise, tagList));

    }

    private List<Tag> parseTags(String[] exerciseTags) {
        try {
            return tagParser.parse(exerciseTags);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    private void persistData(ExerciseStructure exerciseStructure) {
        try {
            exerciseRepositoryImplementation.save(exerciseStructure.exercise);
            List<Tag> persistedTags = persistTags(exerciseStructure.tagList);
            exerciseStructure.exerciseTagList = exerciseTagBuilder.build(exerciseStructure.exercise, persistedTags);
            persistExerciseTags(exerciseStructure.exerciseTagList);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }

    private List<Tag> persistTags(List<Tag> tagList) {
        List<Tag> finalTagList = new ArrayList<>();
        if(tagList != null && tagList.size() > 0) {
            for(Tag tag : tagList) {
                List<Tag> persist = (List) tagRepositoryImplementation.find(tag);
                if(persist.size() == 0) {
                    tagRepositoryImplementation.save(tag);
                } else {
                    tag = persist.get(0);
                }
                finalTagList.add(tag);
            }
        }
        return finalTagList;
    }

    private void persistExerciseTags(List<ExerciseTag> exerciseTagList) {
        if(exerciseTagList != null && exerciseTagList.size() > 0) {
            for(ExerciseTag exerciseTag : exerciseTagList) {
                exerciseTagRepositoryImplementation.save(exerciseTag);
            }
        }
    }
}
