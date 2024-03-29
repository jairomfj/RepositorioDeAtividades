package br.com.repositoriodeatividades.usecases.exercise.utils.vo;


import br.com.repositoriodeatividades.entities.ExerciseEntity;
import br.com.repositoriodeatividades.entities.TagEntity;

import java.util.List;

public class ExerciseStructure {

    public ExerciseEntity exercise;
    public List<TagEntity> tagList;
    public List<TagEntity> exerciseTagList;

    public ExerciseStructure(ExerciseEntity exercise, List<TagEntity> tagList) {
        this.exercise = exercise;
        this.tagList = tagList;
    }

}
