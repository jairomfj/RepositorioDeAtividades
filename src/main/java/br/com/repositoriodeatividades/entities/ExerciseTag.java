package br.com.repositoriodeatividades.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "exercise_tag")
public class ExerciseTag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String label;

    @NotNull
    private Date created;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Exercise exercise;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Tag tag;

    public ExerciseTag() { }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }
}