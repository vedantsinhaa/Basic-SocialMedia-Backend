package com.example.demo.Model;

import java.io.Serializable;

public class DetailUser implements Serializable {
    private long userID;
    private String name;
    private String email;

    public DetailUser() {}

    public DetailUser(long userID, String name, String email) {
        this.userID = userID;
        this.name = name;
        this.email = email;
    }

    public long getUserID() {
        return userID;
    }

    public String getUserName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
