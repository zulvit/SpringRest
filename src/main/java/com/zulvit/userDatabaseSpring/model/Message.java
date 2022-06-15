package com.zulvit.userDatabaseSpring.model;

import javax.persistence.*;

@Entity
public class Message {
    @Id
    @SequenceGenerator(name = "message=sequence", sequenceName = "message=sequence")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String message;

    public Message(int id, String message) {
        this.id = id;
        this.message = message;
    }

    public Message() {

    }

    public int getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
