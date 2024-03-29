package br.com.repositoriodeatividades.usecases.exercise.create.models;

import br.com.repositoriodeatividades.entities.ExerciseEntity;
import br.com.repositoriodeatividades.entities.TagEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;



@Component
public class TagParser {

    public List<TagEntity> parse(String[] tags) {
        List<TagEntity> tagList = new ArrayList<>();
        if(tags != null) {
            for(String tagLabel : tags) {
                TagEntity tag = new TagEntity(tagLabel.toUpperCase());
                tagList.add(tag);
            }
        }
        return tagList;
    }

    public List<TagEntity> parse(String[] tags, ExerciseEntity exercise) {
        List<TagEntity> tagList = parse(tags);
        for(TagEntity tag : tagList) {
            tag.setExercise(exercise);
        }
        return tagList;
    }
}
