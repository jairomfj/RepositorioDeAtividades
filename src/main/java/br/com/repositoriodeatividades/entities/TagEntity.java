package br.com.repositoriodeatividades.entities;

import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.Calendar;
import java.util.Date;

@Entity(name = "Tag")
@Table(name = "tags")
public class TagEntity {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String label;

    @NotNull
    private Date createdAt = Calendar.getInstance().getTime();

    @NotNull @ManyToOne(fetch = FetchType.LAZY)
    private ExerciseEntity exercise;

    public TagEntity() { }

    public TagEntity(String label) {
        this.label = label;
    }

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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public ExerciseEntity getExercise() {
        return exercise;
    }

    public void setExercise(ExerciseEntity exercise) {
        this.exercise = exercise;
    }
}