package com.example.demo.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "COMMENTS")
public class SocialMediaEntityComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long commentID;

    private String commentBody;

    @ManyToOne
    @JoinColumn(name = "USERID")
    private SocialMediaEntityUser user;

    @ManyToOne
    @JoinColumn(name = "POSTID")
    private SocialMediaEntityPost post;

    public SocialMediaEntityComment() {}

    public SocialMediaEntityComment(String CommentMainBody, SocialMediaEntityUser user, SocialMediaEntityPost post) {
        this.commentBody = CommentMainBody;
        this.user = user;
        this.post = post;
    }

    public long getIDofComment() {
        return commentID;
    }

    public void setIDofComment(long IDofComment) {
        this.commentID = IDofComment;
    }

    public String getCommentMainBody() {
        return commentBody;
    }

    public void setCommentMainBody(String CommentMainBody) {
        this.commentBody = CommentMainBody;
    }

    public SocialMediaEntityUser getUser() {
        return user;
    }

    public void setUser(SocialMediaEntityUser user) {
        this.user = user;
    }

    public SocialMediaEntityPost getPost() {
        return post;
    }

    public void setPost(SocialMediaEntityPost post) {
        this.post = post;
    }
}
