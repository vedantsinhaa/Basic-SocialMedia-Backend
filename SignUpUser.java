package com.example.demo.Model;

import java.io.Serializable;

public class SignUpUser implements Serializable {
    private String email;
    private String name;
    private String password;

    public SignUpUser(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
