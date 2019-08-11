package com.kafka.model;

import java.time.Instant;
import java.util.Random;

public class Issue {

    private Integer id;
    private Integer number;
    private String state;
    private String title;
    private Instant created;
    private User user;

    public static Issue newRandom() {
        Random random = new Random();
        Issue issue = new Issue();
        issue.setUser(new User());
        issue.setId(random.nextInt());
        issue.setNumber(random.nextInt());
        issue.setState("Pending");
        issue.setTitle("Title is");
        issue.setCreated(Instant.now());
        issue.getUser().setId(random.nextInt());
        issue.getUser().setLogin("Login");
        return issue;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Instant getCreated() {
        return created;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }
}
