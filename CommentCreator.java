package com.example.demo.Model;

import java.io.Serializable;

public class CommentCreator implements Serializable {
    private long userID;
    private String name;

    public CommentCreator() {};

    public CommentCreator(long userID, String name) {
        this.userID = userID;
        this.name = name;
    }

    public long getUserID() {
        return userID;
    }

    public String getName() {
        return name;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public void setName(String name) {
        this.name = name;
    }
}
