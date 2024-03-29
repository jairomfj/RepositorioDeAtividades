package br.com.repositoriodeatividades.usecases.exercise.retrieve.models.vo;

import br.com.repositoriodeatividades.entities.ExerciseOptionEntity;
import br.com.repositoriodeatividades.entities.TagEntity;
import br.com.repositoriodeatividades.entities.UserEntity;
import br.com.repositoriodeatividades.usecases.exercise.utils.enums.ExerciseLevelType;

import java.util.List;

public class ExerciseVO {

    private Long id;

    private String label;

    private String type;

    private ExerciseLevelType level;

    private UserEntity user;

    private List<ExerciseOptionEntity> exerciseOption;

    private String tags;

    private String options;

    public ExerciseVO(Long id, String label, String type, ExerciseLevelType level, UserEntity user, List<ExerciseOptionEntity> exerciseOption, List<TagEntity> tags, String options) {
        this.id = id;
        this.label = label;
        this.type = type;
        this.level = level;
        this.user = user;
        this.exerciseOption = exerciseOption;
        this.tags = tagsToStringFormat(tags);
        this.options = options;
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

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public List<ExerciseOptionEntity> getExerciseOptions() {
        return exerciseOption;
    }

    public void setExerciseOptions(List<ExerciseOptionEntity> exerciseOption) {
        this.exerciseOption = exerciseOption;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    private String tagsToStringFormat(List<TagEntity> tags) {

        String tagsAsString = "";
        for(TagEntity tag : tags) {
            tagsAsString += tag.getLabel() + ",";
        }
        return tagsAsString;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }
}
