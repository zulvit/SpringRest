package com.zulvit.userDatabaseSpring.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String note;

    public long getId() {
        return id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String message) {
        this.note = message;
    }

    @Override
    public String toString() {
        return "{\n\t\"id:\": " + id + "," +
                "\n\t\"note\" : " + note + "}";
    }
}
