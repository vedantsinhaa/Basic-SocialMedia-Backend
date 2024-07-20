package com.example.demo.Model;
import java.io.Serializable;
import java.util.List;
public class UserModel implements Serializable {
    private String name;
    private long userID;
    private String email;
    private List<PostModel> posts;

    public UserModel() {};

    public UserModel(String name, int userID, String email, List<PostModel> posts) {
        this.name = name;
        this.userID = userID;
        this.email = email;
        this.posts = posts;
    }

    public String getName() {
        return name;
    }

    public long getUserID() {
        return userID;
    }

    public String getEmail() {
        return email;
    }

    public List<PostModel> getPosts() {
        return posts;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPosts(List<PostModel> posts) {
        this.posts = posts;
    }
}
