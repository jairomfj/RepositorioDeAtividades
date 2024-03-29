package br.com.repositoriodeatividades.usecases.exercise.imports.models;

public class ExtractedExercise {

    private String original;

    private String label;
    private String type;
    private String options;

    public ExtractedExercise(String label, String type, String options, String original) {
        this.label = label;
        this.type = type;
        this.options = options;
        this.original = original;
    }

    public String getLabel() {
        return label;
    }

    public String getType() {
        return type;
    }

    public String getOptions() {
        return options;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }
}
