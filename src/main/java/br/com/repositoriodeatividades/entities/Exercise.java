package br.com.repositoriodeatividades.entities;

import br.com.repositoriodeatividades.usecases.exercise.utils.enums.ExerciseLevelType;
import br.com.repositoriodeatividades.usecases.exercise.utils.vo.ExercisePlain;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "exercise")
public class Exercise {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 100000)
    private String label;

    @Column(nullable = false) @DateTimeFormat(pattern="dd-MM-YYYY hh:mm:ss")
    private Date created = Calendar.getInstance().getTime();

    @Column(nullable = true)
    private String type = "";

    @Column(nullable = false)
    private boolean active = true;

    @Column(nullable = false)
    private ExerciseLevelType level;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "exercise", targetEntity = ExerciseOption.class, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ExerciseOption> exerciseOption;

    @OneToMany(mappedBy = "exercise", targetEntity = Tag.class, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Tag> tags;

    public Exercise() {}

    public Exercise(String label, List<ExerciseOption> exerciseOptionList) {
        this.label = label;
        this.exerciseOption = exerciseOptionList;

        for(ExerciseOption exerciseOption : exerciseOptionList) {
            exerciseOption.setExercise(this);
        }
    }

    public Exercise(ExercisePlain exercisePlain, List<ExerciseOption> exerciseOptionList) {
        this.label = exercisePlain.getExerciseLabel();
        this.type = exercisePlain.getExerciseType();
        this.level = exercisePlain.getExerciseLevel();
        this.exerciseOption = exerciseOptionList;

        for(ExerciseOption exerciseOption : exerciseOptionList) {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<ExerciseOption> getExerciseOptions() {
        return exerciseOption;
    }

    public void setExerciseOptions(List<ExerciseOption> questionOption) {
        this.exerciseOption = questionOption;
    }

    public ExerciseLevelType getLevel() {
        return level;
    }

    public void setLevel(ExerciseLevelType level) {
        this.level = level;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
