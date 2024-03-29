package br.com.repositoriodeatividades.usecases.exercise.edit;

import br.com.repositoriodeatividades.entities.Exercise;
import br.com.repositoriodeatividades.entities.ExerciseOption;
import br.com.repositoriodeatividades.entities.Tag;
import br.com.repositoriodeatividades.repositories.interfaces.ExerciseOptionRepositoryInterface;
import br.com.repositoriodeatividades.repositories.interfaces.ExerciseRepositoryInterface;
import br.com.repositoriodeatividades.repositories.interfaces.TagRepositoryInterface;
import br.com.repositoriodeatividades.usecases.exercise.create.models.TagParser;
import br.com.repositoriodeatividades.usecases.exercise.utils.vo.ExercisePlain;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service @Transactional
public class EditExercise {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ExerciseRepositoryInterface exerciseRepository;


    @Autowired
    ExerciseOptionRepositoryInterface exerciseOptionRepository;

    @Autowired
    TagRepositoryInterface tagRepository;

    @Autowired
    TagParser tagParser;

    public void edit(ExercisePlain exercisePlain) {
        log.info("Editing exercise data");
        Exercise exercise = updateExercise(exercisePlain);
        updateExerciseOptions(exercisePlain.getOptionLabel(), exercise);
        updateTags(exercisePlain.getExerciseTags(), exercise);
        log.info("Exercise successfully updated");
    }

    private Exercise updateExercise(ExercisePlain exercisePlain) {
        log.info("Updating exercise");
        Exercise persistedExercise = exerciseRepository.find(exercisePlain.getExerciseId());
        persistedExercise.setLabel(exercisePlain.getExerciseLabel());
        persistedExercise.setLevel(exercisePlain.getExerciseLevel());
        persistedExercise.setType(exercisePlain.getExerciseType());
        exerciseRepository.edit(persistedExercise);
        return persistedExercise;
    }

    private void updateExerciseOptions(String[] newOptions, Exercise exercise) {
        log.info("Updating exercise option");

        List<ExerciseOption> persistedOptions = exercise.getExerciseOptions();
        for(int i = 0; i < persistedOptions.size(); i++) {

            String newOptionLabel = newOptions[i];
            ExerciseOption persistedOption = persistedOptions.get(i);

            if(StringUtils.isEmpty(newOptionLabel)) {
                exerciseOptionRepository.delete(persistedOption);
                continue;
            }

            if(!persistedOption.getLabel().equals(newOptionLabel)) {
                persistedOption.setLabel(newOptions[i]);
                exerciseOptionRepository.edit(persistedOption);
            }
        }
    }


    private List<Tag> updateTags(String[] exerciseTags, Exercise persistedExercise) {
        log.info("Updating exercise tags");

        List<Tag> persistedTags = persistedExercise.getTags();
        List<Tag> newTags = tagParser.parse(exerciseTags, persistedExercise);

        List<Tag> tagsThatShouldBeRemoved = tagsThatShouldBeRemoved(newTags, persistedTags);
        for(Tag tagToBeRemoved : tagsThatShouldBeRemoved)
            tagRepository.delete(tagToBeRemoved);

        for(Tag newTag : newTags) {
            if(!tagIsAlreadyPersisted(newTag, persistedTags))
                tagRepository.save(newTag);
        }

        return newTags;

    }

    private List<Tag> tagsThatShouldBeRemoved(List<Tag> newTags, List<Tag> persistedTags) {
        List<Tag> tagsThatShouldBeRemoved = new ArrayList<>();
        for(Tag persistedTag : persistedTags) {
            boolean found = false;
            for(Tag tag : newTags) {
                if(tag.getLabel().equals(persistedTag.getLabel()))
                    found = true;
            }
            if(!found)
                tagsThatShouldBeRemoved.add(persistedTag);
        }
        return tagsThatShouldBeRemoved;
    }

    private boolean tagIsAlreadyPersisted(Tag newTag, List<Tag> persistedTags) {
        boolean alreadyPersisted = false;
        for(Tag persistedTag : persistedTags) {
            if(persistedTag.getLabel().equals(newTag.getLabel()))
                alreadyPersisted = true;
        }
        return alreadyPersisted;
    }
}
