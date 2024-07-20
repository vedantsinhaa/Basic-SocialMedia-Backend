package com.example.demo.entity;
import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "USERS")
public class SocialMediaEntityUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userID;

    @Column(unique = true, nullable = false)
    private String email;

    private String name;

    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<SocialMediaEntityPost> posts;

    public SocialMediaEntityUser() {}

    public SocialMediaEntityUser(String MailID, String name, String pass) {
        this.email = MailID;
        this.name = name;
        this.password = pass;
    }

    public int getIDofUser() {
        return userID;
    }

    public void setIDofUser(int userID) {
        this.userID = userID;
    }

    public String getUserMailID() {
        return email;
    }

    public void setUserMailID(String MailID) {
        this.email = MailID;
    }

    public String getUserName() {
        return name;
    }

    public void setUserName(String name) {
        this.name = name;
    }

    public String getPasswordofUser() {
        return password;
    }

    public void setPasswordofUser(String pass) {
        this.password = pass;
    }

    public List<SocialMediaEntityPost> getPosts() {
        return posts;
    }

    public void setPosts(List<SocialMediaEntityPost> posts) {
        this.posts = posts;
    }
}
