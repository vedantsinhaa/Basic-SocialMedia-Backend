package com.example.demo.Model;

import java.io.Serializable;

public class CommentDelete implements Serializable {
    private int commentID;

    public CommentDelete() {}

    public CommentDelete(int commentID) {
        this.commentID = commentID;
    }

    public int getCommentID() {
        return commentID;
    }
}
