package br.com.repositoriodeatividades.entities;


import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "tag")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String label;

    @NotNull
    private Date created = Calendar.getInstance().getTime();

    @NotNull @ManyToOne(fetch = FetchType.LAZY)
    private Exercise exercise;

    public Tag() { }

    public Tag(String label) {
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
}