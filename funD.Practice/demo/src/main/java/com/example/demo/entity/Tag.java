package com.example.demo.entity;

import javax.persistence.*;

@Entity
public class Tag {

    @Id
    @GeneratedValue
    private Long tag_id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String tag_contents;

    public Long getTag_id() {
        return tag_id;
    }

    public User getUser() {
        return user;
    }

    public String getTag_contents() {
        return tag_contents;
    }

    public void setTag_id(Long tag_id) {
        this.tag_id = tag_id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setTag_contents(String tag_contents) {
        this.tag_contents = tag_contents;
    }
}
