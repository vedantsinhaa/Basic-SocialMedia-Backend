package com.example.demo.Model;

import java.io.Serializable;

public class CommentNew implements Serializable {
    private String commentBody;
    private long postID;
    private long userID;

    public CommentNew() {}

    public CommentNew(String commentBody, long postID, long userID) {
        this.commentBody = commentBody;
        this.postID = postID;
        this.userID = userID;
    }

    public String getCommentBody() {
        return commentBody;
    }

    public long getPostID() {
        return postID;
    }

    public long getUserID() {
        return userID;
    }
}
