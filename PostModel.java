package com.example.demo.Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class PostModel implements Serializable {
    private long postID;
    private String postBody;
    private LocalDate date;
    private List<CommentBody> comments;

    public PostModel() {}

    public PostModel(long postID, String postBody, Date date, List<CommentBody> comments) {
        this.postID = postID;
        this.postBody = postBody;
        this.date = LocalDate.now();
        this.comments = comments;
    }

    public long getPostID() {
        return postID;
    }

    public String getPostBody() {
        return postBody;
    }

    public String getDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return date.format(formatter);
    }

    public List<CommentBody> getComments() {
        return comments;
    }

    public void setPostID(long postID) {
        this.postID = postID;
    }

    public void setPostBody(String postBody) {
        this.postBody = postBody;
    }

    public void setDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.date = LocalDate.parse(date, formatter);
    }

    public void setComments(List<CommentBody> comments) {
        this.comments = comments;
    }
}
