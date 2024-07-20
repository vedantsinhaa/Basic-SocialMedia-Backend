package com.example.demo.entity;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "POSTS")
public class SocialMediaEntityPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postID;

    private String postBody;

    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "USERID")
    private SocialMediaEntityUser user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<SocialMediaEntityComment> comments;

    public SocialMediaEntityPost() {}

    public SocialMediaEntityPost(String MainBody, SocialMediaEntityUser user) {
        this.postBody = MainBody;
        this.date = LocalDate.now();
        this.user = user;
    }

    public int getIDofPost() {
        return postID;
    }

    public void setIDofPost(int postID) {
        this.postID = postID;
    }

    public String getMainBodyofPost() {
        return postBody;
    }

    public void setMainBodyofPost(String MainBody) {
        this.postBody = MainBody;
    }

    public String getDateofPost() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


        return date.format(formatter);
    }

    public void setDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.date = LocalDate.parse(date, formatter);
    }

    public SocialMediaEntityUser getUser() {
        return user;
    }

    public void setUser(SocialMediaEntityUser user) {
        this.user = user;
    }

    public List<SocialMediaEntityComment> getComments() {
        return comments;
    }

    public void setComments(List<SocialMediaEntityComment> comments) {
        this.comments = comments;
    }
}
