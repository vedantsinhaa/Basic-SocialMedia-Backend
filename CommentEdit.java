package com.example.demo.Model;

import java.io.Serializable;

public class CommentEdit implements Serializable {
    private long commentID;
    private String commentBody;

    public CommentEdit() {}

    public CommentEdit(long commentID, String commentBody) {
        this.commentID = commentID;
        this.commentBody = commentBody;
    }

    public String getCommentBody() {
        return commentBody;
    }

    public long getCommentID() {
        return commentID;
    }
}
