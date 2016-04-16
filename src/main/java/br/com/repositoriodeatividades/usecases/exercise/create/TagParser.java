package br.com.repositoriodeatividades.usecases.exercise.create;

import br.com.repositoriodeatividades.entities.Tag;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;



@Component
public class TagParser {

    public List<Tag> parse(String[] tags) throws Exception {

        if(tags == null) {
            throw new IllegalArgumentException("Tags list cannot be null");
        }

        List<Tag> tagList = new ArrayList<>();
        for(String tagLabel : tags) {
            String tagLabelUp =  tagLabel.toUpperCase();
            if(!tagAlreadyAdded(tagLabelUp, tagList)) {
                Tag tag = new Tag(tagLabelUp);
                tagList.add(tag);
            }
        }

        return tagList;
    }

    private boolean tagAlreadyAdded(String tagToBeChecked, List<Tag> tagList) {
        for(Tag tagInList : tagList) {
            if(tagToBeChecked.equals(tagInList.getLabel())) {
                return true;
            }
        }
        return false;
    }
}
