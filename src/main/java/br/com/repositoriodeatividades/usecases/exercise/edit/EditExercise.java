package br.com.repositoriodeatividades.usecases.exercise.edit;

import br.com.repositoriodeatividades.entities.ExerciseEntity;
import br.com.repositoriodeatividades.entities.ExerciseOptionEntity;
import br.com.repositoriodeatividades.entities.TagEntity;
import br.com.repositoriodeatividades.repositories.interfaces.ExerciseOptionRepositoryInterface;
import br.com.repositoriodeatividades.repositories.interfaces.ExerciseRepositoryInterface;
import br.com.repositoriodeatividades.repositories.interfaces.TagRepositoryInterface;
import br.com.repositoriodeatividades.usecases.exercise.create.models.TagParser;
import br.com.repositoriodeatividades.usecases.exercise.utils.enums.ExerciseType;
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
        ExerciseEntity exercise = updateExercise(exercisePlain);
        updateExerciseOptions(exercisePlain.getOptionLabel(), exercise);
        updateTags(exercisePlain.getExerciseTags(), exercise);
        log.info("Exercise successfully updated");
    }

    private ExerciseEntity updateExercise(ExercisePlain exercisePlain) {
        log.info("Updating exercise");
        ExerciseEntity persistedExercise = exerciseRepository.find(exercisePlain.getExerciseId());
        persistedExercise.setText(exercisePlain.getExerciseLabel());
        persistedExercise.setLevel(exercisePlain.getExerciseLevel());
        persistedExercise.setType(ExerciseType.valueOf(exercisePlain.getExerciseType()));
        persistedExercise.setOptions(exercisePlain.getExerciseOptions());
        exerciseRepository.edit(persistedExercise);
        return persistedExercise;
    }

    private void updateExerciseOptions(String[] newOptions, ExerciseEntity exercise) {
        log.info("Updating exercise option");

        List<ExerciseOptionEntity> persistedOptions = exercise.getExerciseOptions();
        for(int i = 0; i < persistedOptions.size(); i++) {

            String newOptionLabel = newOptions[i];
            ExerciseOptionEntity persistedOption = persistedOptions.get(i);

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


    private List<TagEntity> updateTags(String[] exerciseTags, ExerciseEntity persistedExercise) {
        log.info("Updating exercise tags");

        List<TagEntity> persistedTags = persistedExercise.getTags();
        List<TagEntity> newTags = tagParser.parse(exerciseTags, persistedExercise);

        List<TagEntity> tagsThatShouldBeRemoved = tagsThatShouldBeRemoved(newTags, persistedTags);
        for(TagEntity tagToBeRemoved : tagsThatShouldBeRemoved)
            tagRepository.delete(tagToBeRemoved);

        for(TagEntity newTag : newTags) {
            if(!tagIsAlreadyPersisted(newTag, persistedTags))
                tagRepository.save(newTag);
        }

        return newTags;

    }

    private List<TagEntity> tagsThatShouldBeRemoved(List<TagEntity> newTags, List<TagEntity> persistedTags) {
        List<TagEntity> tagsThatShouldBeRemoved = new ArrayList<>();
        for(TagEntity persistedTag : persistedTags) {
            boolean found = false;
            for(TagEntity tag : newTags) {
                if(tag.getLabel().equals(persistedTag.getLabel()))
                    found = true;
            }
            if(!found)
                tagsThatShouldBeRemoved.add(persistedTag);
        }
        return tagsThatShouldBeRemoved;
    }

    private boolean tagIsAlreadyPersisted(TagEntity newTag, List<TagEntity> persistedTags) {
        boolean alreadyPersisted = false;
        for(TagEntity persistedTag : persistedTags) {
            if(persistedTag.getLabel().equals(newTag.getLabel()))
                alreadyPersisted = true;
        }
        return alreadyPersisted;
    }
}
