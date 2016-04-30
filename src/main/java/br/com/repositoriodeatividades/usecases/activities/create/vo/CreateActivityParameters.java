package br.com.repositoriodeatividades.usecases.activities.create.vo;

import br.com.repositoriodeatividades.entities.User;

/**
 * Created by jairomendes on 4/16/16.
 */
public class CreateActivityParameters {

    private int id;
    private String level;
    private int amount;
    private String[] tags;
    private String username;
    private User user;

    public CreateActivityParameters(Integer id, String level, int amount, String[] tags, String username) {
        this.id = id;
        this.level = level;
        this.amount = amount;
        this.tags = tags;
        this.username = username;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
