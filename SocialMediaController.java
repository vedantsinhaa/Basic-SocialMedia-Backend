package com.example.demo.api.controller;

import com.example.demo.Model.*;
import com.example.demo.Service.SocialMediaService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SocialMediaController {
    private final SocialMediaService socialMediaService;

    @Autowired
    public SocialMediaController(SocialMediaService socialMediaService) {
        this.socialMediaService = socialMediaService;
    }

    @PostMapping("/comment")
    public ResponseEntity<Object> createComment(@RequestBody CommentNew request) {
        String result = socialMediaService.createComment(request.getCommentBody(), request.getPostID(), request.getUserID());
        if (result.equals("Comment created successfully")) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorInResponse(result));
        }
    }

    @GetMapping("/comment")
    public ResponseEntity<Object> getCommentById(@RequestParam long commentID) {
        Object result = socialMediaService.getCommentById(commentID);
        if (result instanceof CommentBody) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorInResponse("Comment does not exist"));
        }
    }

    @PatchMapping("/comment")
    public ResponseEntity<Object> editComment(@RequestBody CommentEdit request) {
        String result = socialMediaService.editComment(request.getCommentID(), request.getCommentBody());
        System.out.println(result);
        if (result.equals("Comment edited successfully")) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorInResponse(result));
        }
    }

    @DeleteMapping("/comment")
    public ResponseEntity<Object> deleteComment(@RequestBody CommentDelete request) {
        String result = socialMediaService.deleteComment(request.getCommentID());
        if (result.equals("Comment deleted")) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorInResponse(result));
        }
    }



    @PostMapping("/post")
    public ResponseEntity<Object> createPost(@RequestBody PostNew request) {
        String result = socialMediaService.createPost(request.getPostBody(), request.getUserID());
        if (result.equals("Post created successfully")) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorInResponse(result));
        }
    }

    @GetMapping("/post/{postID}")
    public ResponseEntity<Object> getPost(@PathVariable int postID) {
        Object result = socialMediaService.getPost(postID);
        if (result instanceof String) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorInResponse("Post does not exist"));
        } else {
            return ResponseEntity.ok(result);
        }
    }

    @PatchMapping("/post")
    public ResponseEntity<Object> editPost(@RequestBody PostEdit request) {
        String result = socialMediaService.editPost(request.getPostID(), request.getPostBody());
        if (result.equals("Post edited successfully")) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorInResponse(result));
        }
    }

    @DeleteMapping("/post")
    public ResponseEntity<Object> deletePost(@RequestBody PostDelete request) {
        String result = socialMediaService.deletePost(request.getPostID());
        if (result.equals("Post deleted")) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorInResponse(result));
        }
    }


    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginUser request) {
        String result = socialMediaService.login(request.getEmail(), request.getPassword());
        if (result.equals("Login Successful")) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorInResponse(result));
        }
    }


    @PostMapping("/signup")
    public ResponseEntity<Object> signup(@RequestBody SignUpUser request) {
        String result = socialMediaService.signup(request.getEmail(), request.getName(), request.getPassword());
        if (result.equals("Account Creation Successful")) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorInResponse(result));
        }
    }

    @GetMapping("/user")
    public ResponseEntity<Object> getUserDetails(@RequestParam long userID) {
        Object result = socialMediaService.getUserDetails(userID);
        if (result instanceof String) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorInResponse("User does not exist"));
        } else {
            return ResponseEntity.ok(result);
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<PostModel>> getUserFeed() {
        List<PostModel> userFeed = socialMediaService.getUserFeed();
        return ResponseEntity.ok(userFeed);
    }

    @GetMapping("/users")
    public List<DetailUser> getAllUsers() {
        return socialMediaService.getAllUsers();
    }

}


