package br.com.repositoriodeatividades.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

    @OneToMany(mappedBy = "tag", targetEntity = ExerciseTag.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Tag> tag;

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
}