package br.com.repositoriodeatividades.usecases.exercise.vo;


import br.com.repositoriodeatividades.entities.Exercise;
import br.com.repositoriodeatividades.entities.Tag;

import java.util.List;

public class ExerciseStructure {

    public Exercise exercise;
    public List<Tag> tagList;
    public List<Tag> exerciseTagList;

    public ExerciseStructure(Exercise exercise, List<Tag> tagList) {
        this.exercise = exercise;
        this.tagList = tagList;
    }

}
