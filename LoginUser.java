package com.example.demo.Model;

import java.io.Serializable;

public class LoginUser implements Serializable {
    private String email;
    private String password;

    public LoginUser(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
