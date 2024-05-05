package br.com.repositoriodeatividades.entities;

import br.com.repositoriodeatividades.usecases.exercise.utils.enums.ExerciseLevel;
import br.com.repositoriodeatividades.usecases.exercise.utils.enums.ExerciseType;
import br.com.repositoriodeatividades.usecases.exercise.utils.vo.CreateExerciseInput;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.*;

import static br.com.repositoriodeatividades.usecases.exercise.utils.enums.ExerciseType.UNKNOWN;

@Entity(name = "Exercise")
@Table(name = "exercises")
public class ExerciseEntity {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 100000)
    private String text;

    @Column(nullable = false) @DateTimeFormat(pattern="dd-MM-YYYY hh:mm:ss")
    private Date createdAt = Calendar.getInstance().getTime();

    @Column()
    private ExerciseType type = UNKNOWN;

    @Column(nullable = false)
    private boolean active = true;

    @Column(nullable = false)
    private ExerciseLevel level;

    @ManyToOne
    private UserEntity user;

    @OneToMany(mappedBy = "exercise", targetEntity = ExerciseOptionEntity.class, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ExerciseOptionEntity> exerciseOption;

    @OneToMany(mappedBy = "exercise", targetEntity = TagEntity.class, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<TagEntity> tags;

    @Column(nullable = false, length = 36, name = "external_id")
    private String externalId;

    @Column(length = 100000)
    private String options;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "exercise_categories", joinColumns = @JoinColumn(name = "exercise_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<CategoryEntity> categories;

    public ExerciseEntity() {}

    public ExerciseEntity(String text, List<ExerciseOptionEntity> exerciseOptionList) {
        this.text = text;
        this.exerciseOption = exerciseOptionList;

        for(ExerciseOptionEntity exerciseOption : exerciseOptionList) {
            exerciseOption.setExercise(this);
        }
    }

    public ExerciseEntity(CreateExerciseInput exercisePlain, List<ExerciseOptionEntity> exerciseOptionList) {
        this.text = exercisePlain.getExerciseLabel();
        this.type = ExerciseType.valueOf(exercisePlain.getExerciseType());
        this.level = exercisePlain.getExerciseLevel();
        this.options = exercisePlain.getExerciseOptions();
        this.exerciseOption = exerciseOptionList;
        this.externalId = UUID.randomUUID().toString();

        for(ExerciseOptionEntity exerciseOption : exerciseOptionList) {
            exerciseOption.setExercise(this);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public ExerciseType getType() {
        return type;
    }

    public void setType(ExerciseType type) {
        this.type = type;
    }

    public List<ExerciseOptionEntity> getExerciseOptions() {
        return exerciseOption;
    }

    public void setExerciseOptions(List<ExerciseOptionEntity> questionOption) {
        this.exerciseOption = questionOption;
    }

    public ExerciseLevel getLevel() {
        return level;
    }

    public void setLevel(ExerciseLevel level) {
        this.level = level;
    }

    public List<TagEntity> getTags() {
        return tags;
    }

    public void setTags(List<TagEntity> tags) {
        this.tags = tags;
    }

    public UUID getExternalId() {
        return UUID.fromString(this.externalId);
    }

    public void setExternalId(UUID externalId) {
        this.externalId = externalId.toString();
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public Set<CategoryEntity> getCategories() {
        return categories;
    }

    public void setCategories(Set<CategoryEntity> categories) {
        this.categories = categories;
    }
}
