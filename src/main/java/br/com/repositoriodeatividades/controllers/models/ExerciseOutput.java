package br.com.repositoriodeatividades.controllers.models;

import br.com.repositoriodeatividades.usecases.exercise.utils.enums.ExerciseLevelType;

import java.util.Date;
import java.util.UUID;

public class ExerciseOutput {

    private static final int LABEL_LENGTH = 60;

    private UUID externalId;
    private String label;
    private Date created;
    private String type;
    private boolean active;
    private ExerciseLevelType level;

    public ExerciseOutput(UUID externalId, String label, Date created, String type, boolean active, ExerciseLevelType level) {
        this.externalId = externalId;
        this.label = label.substring(0, getEndIndex(label)).concat("...");
        this.created = created;
        this.type = type;
        this.active = active;
        this.level = level;
    }

    public UUID getExternalId() {
        return externalId;
    }

    public String getLabel() {
        return label;
    }

    public Date getCreated() {
        return created;
    }

    public String getType() {
        return type;
    }

    public boolean isActive() {
        return active;
    }

    public ExerciseLevelType getLevel() {
        return level;
    }

    private int getEndIndex(String label) {
        int lableLength = label.length();
        return lableLength < LABEL_LENGTH ? lableLength : LABEL_LENGTH - 1;
    }
}
