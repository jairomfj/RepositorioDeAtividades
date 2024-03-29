package br.com.repositoriodeatividades.entities;

import br.com.repositoriodeatividades.usecases.exercise.utils.enums.ExerciseLevelType;
import br.com.repositoriodeatividades.usecases.exercise.utils.vo.CreateExerciseInput;
import br.com.repositoriodeatividades.usecases.exercise.utils.vo.ExercisePlain;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity(name = "Exercise")
@Table(name = "exercises")
public class ExerciseEntity {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 100000)
    private String label;

    @Column(nullable = false) @DateTimeFormat(pattern="dd-MM-YYYY hh:mm:ss")
    private Date created = Calendar.getInstance().getTime();

    @Column()
    private String type = "";

    @Column(nullable = false)
    private boolean active = true;

    @Column(nullable = false)
    private ExerciseLevelType level;

    @ManyToOne
    private UserEntity user;

    @OneToMany(mappedBy = "exercise", targetEntity = ExerciseOptionEntity.class, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ExerciseOptionEntity> exerciseOption;

    @OneToMany(mappedBy = "exercise", targetEntity = TagEntity.class, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<TagEntity> tags;

    @Column(nullable = false, length = 36, name = "external_id")
    private String externalId;

    @Column(nullable = false, length = 100000)
    private String options;

    public ExerciseEntity() {}

    public ExerciseEntity(String label, List<ExerciseOptionEntity> exerciseOptionList) {
        this.label = label;
        this.exerciseOption = exerciseOptionList;

        for(ExerciseOptionEntity exerciseOption : exerciseOptionList) {
            exerciseOption.setExercise(this);
        }
    }

    public ExerciseEntity(CreateExerciseInput exercisePlain, List<ExerciseOptionEntity> exerciseOptionList) {
        this.label = exercisePlain.getExerciseLabel();
        this.type = exercisePlain.getExerciseType();
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

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<ExerciseOptionEntity> getExerciseOptions() {
        return exerciseOption;
    }

    public void setExerciseOptions(List<ExerciseOptionEntity> questionOption) {
        this.exerciseOption = questionOption;
    }

    public ExerciseLevelType getLevel() {
        return level;
    }

    public void setLevel(ExerciseLevelType level) {
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
}
