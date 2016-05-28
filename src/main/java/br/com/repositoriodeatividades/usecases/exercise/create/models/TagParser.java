package br.com.repositoriodeatividades.usecases.exercise.create.models;

import br.com.repositoriodeatividades.entities.Exercise;
import br.com.repositoriodeatividades.entities.Tag;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;



@Component
public class TagParser {

    public List<Tag> parse(String[] tags) {
        List<Tag> tagList = new ArrayList<>();
        if(tags != null) {
            for(String tagLabel : tags) {
                Tag tag = new Tag(tagLabel.toUpperCase());
                tagList.add(tag);
            }
        }
        return tagList;
    }

    public List<Tag> parse(String[] tags, Exercise exercise) {
        List<Tag> tagList = parse(tags);
        for(Tag tag : tagList) {
            tag.setExercise(exercise);
        }
        return tagList;
    }
}
