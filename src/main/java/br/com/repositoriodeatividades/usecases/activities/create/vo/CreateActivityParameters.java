package br.com.repositoriodeatividades.usecases.activities.create.vo;

import br.com.repositoriodeatividades.usecases.activities.create.CreateActivity;

/**
 * Created by jairomendes on 4/16/16.
 */
public class CreateActivityParameters {

    private int id;
    private String level;
    private int ammount;
    private String[] tags;

    public CreateActivityParameters(int id, String level, int ammount, String[] tags) {
        this.id = id;
        this.level = level;
        this.ammount = ammount;
        this.tags = tags;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getAmmount() {
        return ammount;
    }

    public void setAmmount(int ammount) {
        this.ammount = ammount;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }
}
