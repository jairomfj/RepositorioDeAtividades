package br.com.repositoriodeatividades.usecases.exercise.edit;

import br.com.repositoriodeatividades.entities.Exercise;
import br.com.repositoriodeatividades.entities.Tag;
import br.com.repositoriodeatividades.repositories.interfaces.ExerciseRepositoryInterface;
import br.com.repositoriodeatividades.repositories.interfaces.TagRepositoryInterface;
import br.com.repositoriodeatividades.usecases.exercise.create.models.TagParser;
import br.com.repositoriodeatividades.usecases.exercise.utils.vo.ExercisePlain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class EditExercise {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ExerciseRepositoryInterface exerciseRepository;

    @Autowired
    TagRepositoryInterface tagRepository;

    @Autowired
    TagParser tagParser;

    public void edit(ExercisePlain exercisePlain) {

        Exercise exercise = updateExercise(exercisePlain);
        exercise.setTags(updateTags(exercisePlain.getExerciseTags(), exercise));
        exerciseRepository.edit(exercise);


        log.info("oi");

    }

    @Transactional
    private Exercise updateExercise(ExercisePlain exercisePlain) {
        Exercise persistedExercise = exerciseRepository.find(exercisePlain.getExerciseId());
        persistedExercise.setLabel(exercisePlain.getExerciseLabel());
        persistedExercise.setLevel(exercisePlain.getExerciseLevel());
        persistedExercise.setType(exercisePlain.getExerciseType());

        return persistedExercise;
    }


    @Transactional
    private List<Tag> updateTags(String[] exerciseTags, Exercise persistedExercise) {

        Collection<Tag> persistedTags = persistedExercise.getTags();
        List<Tag> newTags = tagParser.parse(exerciseTags, persistedExercise);

        List<Tag> tagsThatShouldBeRemoved = tagsThatShouldBeRemoved(newTags, (List) persistedTags);
        for(Tag tagToBeRemoved : tagsThatShouldBeRemoved) {
            tagRepository.delete(tagToBeRemoved);
        }

        return newTags;

    }

    private List<Tag> tagsThatShouldBeRemoved(List<Tag> newTags, List<Tag> persistedTags) {
        List<Tag> tagsThatShouldBeRemoved = new ArrayList<>();
        for(Tag persistedTag : persistedTags) {
            boolean found = false;
            for(Tag tag : newTags) {
                if(tag.getLabel().equals(persistedTag.getLabel())) {
                    found = true;
                }
            }
            if(!found)
                tagsThatShouldBeRemoved.add(persistedTag);
        }
        return tagsThatShouldBeRemoved;
    }

    private List<Tag> tagsThatShouldBePersisted(List<Tag> newTags, List<Tag> persistedTags) {
        List<Tag> tagsThatShouldBePersisted = new ArrayList<>();
        for(Tag newTag : newTags) {
            boolean found = false;
            for(Tag persistedTag : persistedTags) {
                if(persistedTag.getLabel().equals(newTag.getLabel())) {
                    found = true;
                }
            }
            if(!found)
                tagsThatShouldBePersisted.add(newTag);
        }
        return tagsThatShouldBePersisted;
    }
}
