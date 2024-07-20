package com.example.demo.Model;
import java.io.Serializable;
public class CommentBody implements Serializable {
    private long commentID;
    private String commentBody;
    private CommentCreator commentCreator;

    public CommentBody() {};

    public CommentBody(long commentID, String commentBody, CommentCreator commentCreator) {
        this.commentID = commentID;
        this.commentBody = commentBody;
        this.commentCreator = commentCreator;
    }

    public long getCommentID() {
        return commentID;
    }

    public String getCommentBody() {
        return commentBody;
    }

    public CommentCreator getCommentCreator() {
        return commentCreator;
    }

    public void setCommentID(long commentID) {
        this.commentID = commentID;
    }

    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody;
    }

    public void setCommentCreator(CommentCreator commentCreator) {
        this.commentCreator = commentCreator;
    }
}
