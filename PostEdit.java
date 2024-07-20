package com.example.demo.Model;

import java.io.Serializable;

public class PostEdit implements Serializable {
    private long postID;
    private String postBody;

    public PostEdit() {}

    public PostEdit(long postID, String postBody) {
        this.postID = postID;
        this.postBody = postBody;
    }

    public long getPostID() {
        return postID;
    }

    public String getPostBody() {
        return postBody;
    }
}
