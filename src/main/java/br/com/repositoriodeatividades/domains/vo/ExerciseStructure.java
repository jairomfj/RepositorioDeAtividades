package br.com.repositoriodeatividades.domains.vo;


import br.com.repositoriodeatividades.entities.Exercise;
import br.com.repositoriodeatividades.entities.ExerciseTag;
import br.com.repositoriodeatividades.entities.Tag;

import java.util.List;

public class ExerciseStructure {

    public Exercise exercise;
    public List<Tag> tagList;
    public List<ExerciseTag> exerciseTagList;

    public ExerciseStructure(Exercise exercise, List<Tag> tagList) {
        this.exercise = exercise;
        this.tagList = tagList;
    }

}
