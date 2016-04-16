package br.com.repositoriodeatividades.usecases.exercise.create;

import br.com.repositoriodeatividades.entities.Exercise;
import br.com.repositoriodeatividades.entities.ExerciseTag;
import br.com.repositoriodeatividades.entities.Tag;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ExerciseTagBuilder {

    public List<ExerciseTag> build(Exercise exercise, List<Tag> tags) {
        List<ExerciseTag> exerciseTags = new ArrayList<>();
        for(Tag tag : tags) {
            exerciseTags.add(new ExerciseTag(exercise, tag));
        }
        return exerciseTags;
    }

}
