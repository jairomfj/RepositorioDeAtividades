package br.com.repositoriodeatividades.entities;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "exercise_option")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@jsonId")

public class ExerciseOption {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String label;

    @Column(nullable = false)
    private Date created = Calendar.getInstance().getTime();

    @Column(nullable = false)
    private boolean active = true;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Exercise exercise;

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
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

    public Exercise getQuestion() {
        return exercise;
    }

    public void setQuestion(Exercise exercise) {
        this.exercise = exercise;
    }

}
