package br.com.repositoriodeatividades.usecases.exercise.vo;

import br.com.repositoriodeatividades.usecases.exercise.interfaces.ExerciseItem;

public class MultipleChoiceExerciseItem implements ExerciseItem {

    public Long id;
    public String label;

    public MultipleChoiceExerciseItem(Long id) {
        this.id = id;
        this.label = "";
    }

    public MultipleChoiceExerciseItem(Long id, String label) {
        this.id = id;
        this.label = label;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getLabel() {
        return label;
    }
}
