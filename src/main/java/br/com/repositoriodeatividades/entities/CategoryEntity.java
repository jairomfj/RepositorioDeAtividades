package br.com.repositoriodeatividades.entities;

import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.Calendar;
import java.util.Date;

@Entity(name = "Category")
@Table(name = "categories")
public class CategoryEntity {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String name;

    @NotNull
    private Date created = Calendar.getInstance().getTime();

    @NotNull @ManyToMany(fetch = FetchType.LAZY)
    private ExerciseEntity exercise;

    public CategoryEntity() { }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setLabel(String name) {
        this.name = name;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}