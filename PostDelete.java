package com.example.demo.Model;

import java.io.Serializable;

public class PostDelete implements Serializable {
    private long postID;

    public PostDelete() {}

    public PostDelete(long postID) {
        this.postID = postID;
    }

    public long getPostID() {
        return postID;
    }
}
