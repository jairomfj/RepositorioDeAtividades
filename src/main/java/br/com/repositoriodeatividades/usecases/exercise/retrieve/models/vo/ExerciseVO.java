package br.com.repositoriodeatividades.usecases.exercise.retrieve.models.vo;

import br.com.repositoriodeatividades.entities.ExerciseOption;
import br.com.repositoriodeatividades.entities.Tag;
import br.com.repositoriodeatividades.entities.User;
import br.com.repositoriodeatividades.usecases.exercise.utils.enums.ExerciseLevelType;

import java.util.List;

public class ExerciseVO {

    private Long id;

    private String label;

    private String type;

    private ExerciseLevelType level;

    private User user;

    private List<ExerciseOption> exerciseOption;

    private String tags;

    public ExerciseVO(Long id, String label, String type, ExerciseLevelType level, User user, List<ExerciseOption> exerciseOption, List<Tag> tags) {
        this.id = id;
        this.label = label;
        this.type = type;
        this.level = level;
        this.user = user;
        this.exerciseOption = exerciseOption;
        this.tags = tagsToStringFormat(tags);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ExerciseLevelType getLevel() {
        return level;
    }

    public void setLevel(ExerciseLevelType level) {
        this.level = level;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<ExerciseOption> getExerciseOptions() {
        return exerciseOption;
    }

    public void setExerciseOptions(List<ExerciseOption> exerciseOption) {
        this.exerciseOption = exerciseOption;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    private String tagsToStringFormat(List<Tag> tags) {

        String tagsAsString = "";
        for(Tag tag : tags) {
            tagsAsString += tag.getLabel() + ",";
        }
        return tagsAsString;
    }
}
