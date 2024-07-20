package com.example.demo.Model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class PostNew implements Serializable {
    private long postID;
    private long userID;
    private String postBody;
    private Date date;
    private List<CommentBody> comments;

    public PostNew() {}

    public PostNew(long postID, long userID, String postBody, Date date, List<CommentBody> comments) {
        this.postID = postID;
        this.userID = userID;
        this.postBody = postBody;
        this.date = date;
        this.comments = comments;
    }

    public long getPostID() {
        return postID;
    }

    public long getUserID() {
        return userID;
    }

    public String getPostBody() {
        return postBody;
    }

    public Date getDate() {
        return date;
    }

    public List<CommentBody> getComments() {
        return comments;
    }
}
