package br.com.repositoriodeatividades.entities;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "exercise")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@jsonId")
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String label;

    @Column(nullable = false)
    private Date created = Calendar.getInstance().getTime();

    @Column(nullable = true)
    private String type = "";

    @Column(nullable = false)
    private boolean deleted = false;

    @Column(nullable = true)
    private String enumerationType;

    @ManyToOne
    private User teacher;


    @OneToMany(mappedBy = "exercise", targetEntity = ExerciseOption.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ExerciseOption> exerciseOption;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEnumerationType() {
        return enumerationType;
    }

    public void setEnumerationType(String enumerationType) {
        this.enumerationType = enumerationType;
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

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public User getTeacher() {
        return teacher;
    }

    public void setTeacher(User teacher) {
        this.teacher = teacher;
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
}
