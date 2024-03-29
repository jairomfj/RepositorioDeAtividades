package br.com.repositoriodeatividades.entities;

import jakarta.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity(name = "ExerciseOption")
@Table(name = "exercise_options")
public class ExerciseOptionEntity {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = true, length = 100000)
    private String label;

    @Column(nullable = false)
    private Date created = Calendar.getInstance().getTime();

    @Column(nullable = false)
    private boolean active = true;

    @ManyToOne(fetch = FetchType.LAZY)
    private ExerciseEntity exercise;

    public ExerciseOptionEntity() {
    }

    public ExerciseEntity getExercise() {
        return exercise;
    }

    public void setExercise(ExerciseEntity exercise) {
        this.exercise = exercise;
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

    public ExerciseEntity getQuestion() {
        return exercise;
    }

    public void setQuestion(ExerciseEntity exercise) {
        this.exercise = exercise;
    }

}
