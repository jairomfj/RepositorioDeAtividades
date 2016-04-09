package br.com.repositoriodeatividades.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "exercise_tag")
public class ExerciseTag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private Date created = Calendar.getInstance().getTime();

    @ManyToOne(fetch = FetchType.LAZY)
    private Exercise exercise;

    @ManyToOne(fetch = FetchType.LAZY)
    private Tag tag;

    public ExerciseTag() { }

    public ExerciseTag(Exercise exercise, Tag tag) {
        this.exercise = exercise;
        this.tag = tag;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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