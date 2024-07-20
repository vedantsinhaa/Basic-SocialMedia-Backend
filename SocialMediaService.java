package com.example.demo.Service;

import com.example.demo.Model.*;
import com.example.demo.entity.SocialMediaEntityComment;
import com.example.demo.entity.SocialMediaEntityPost;
import com.example.demo.entity.SocialMediaEntityUser;
import com.example.demo.repository.RepositoryComment;
import com.example.demo.repository.RepositoryPost;
import com.example.demo.repository.RepositoryUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SocialMediaService {
    private final RepositoryUser repositoryUser;
    private final RepositoryPost repositoryPost;
    private final RepositoryComment repositoryComment;

    @Autowired
    public SocialMediaService(RepositoryUser repositoryUser, RepositoryComment repositoryComment, RepositoryPost repositoryPost) {
        this.repositoryUser = repositoryUser;
        this.repositoryPost = repositoryPost;
        this.repositoryComment = repositoryComment;
    }


    public String createComment(String commentBody, long postID, long userID) {
        Optional<SocialMediaEntityUser> userEntityOptional = getUserByID(userID);
        if (userEntityOptional.isPresent()) {
            Optional<SocialMediaEntityPost> postEntityOptional = getPostByID(postID);
            if (postEntityOptional.isPresent()) {
                saveComment(commentBody, postEntityOptional.get(), userEntityOptional.get());
                return "Comment created successfully";
            } else {
                return "Post does not exist";
            }
        } else {
            return "User does not exist";
        }
    }

    private Optional<SocialMediaEntityUser> getUserByID(long userID) {
        return repositoryUser.findById(userID);
    }

    private Optional<SocialMediaEntityPost> getPostByID(long postID) {
        return repositoryPost.findById(postID);
    }

    private void saveComment(String commentBody, SocialMediaEntityPost post, SocialMediaEntityUser user) {
        SocialMediaEntityComment commentEntity = new SocialMediaEntityComment();
        commentEntity.setCommentMainBody(commentBody);
        commentEntity.setPost(post);
        commentEntity.setUser(user);
        repositoryComment.save(commentEntity);
    }


    public Object getCommentById(long commentID) {
        Optional<SocialMediaEntityComment> commentEntityOptional = getCommentEntityByID(commentID);
        if (commentEntityOptional.isPresent()) {
            SocialMediaEntityComment commentEntity = commentEntityOptional.get();
            return mapCommentEntityToCommentBody(commentEntity);
        } else {
            return "Comment does not exist";
        }
    }

    private Optional<SocialMediaEntityComment> getCommentEntityByID(long commentID) {
        return repositoryComment.findById(commentID);
    }

    private CommentBody mapCommentEntityToCommentBody(SocialMediaEntityComment commentEntity) {
        CommentBody comment = new CommentBody();
        comment.setCommentID(commentEntity.getIDofComment());
        comment.setCommentBody(commentEntity.getCommentMainBody());
        CommentCreator commentCreator = new CommentCreator();
        commentCreator.setUserID(commentEntity.getUser().getIDofUser());
        commentCreator.setName(commentEntity.getUser().getUserName());
        comment.setCommentCreator(commentCreator);
        return comment;
    }



    public String editComment(long commentID, String newCommentBody) {
        Optional<SocialMediaEntityComment> commentEntityOptional = getCommentEntityByID(commentID);
        if (commentEntityOptional.isPresent()) {
            SocialMediaEntityComment commentEntity = commentEntityOptional.get();
            updateCommentBody(commentEntity, newCommentBody);
            saveCommentEntity(commentEntity);
            return "Comment edited successfully";
        } else {
            return "Comment does not exist";
        }
    }


    private void updateCommentBody(SocialMediaEntityComment commentEntity, String newCommentBody) {
        commentEntity.setCommentMainBody(newCommentBody);
    }

    private void saveCommentEntity(SocialMediaEntityComment commentEntity) {
        repositoryComment.save(commentEntity);
    }


    public String deleteComment(long commentID) {
        Optional<SocialMediaEntityComment> commentEntityOptional = repositoryComment.findById(commentID);
        if (commentEntityOptional.isPresent()) {
            repositoryComment.deleteById(commentID);
            return "Comment deleted";
        } else {
            return "Comment does not exist";
        }
    }

    public String createPost(String postBody, long userID) {
        Optional<SocialMediaEntityUser> userEntityOptional = repositoryUser.findById(userID);
        if (userEntityOptional.isPresent()) {
            SocialMediaEntityUser userEntity = userEntityOptional.get();
            SocialMediaEntityPost newPostEntity = new SocialMediaEntityPost(postBody, userEntity);
            repositoryPost.save(newPostEntity);
            return "Post created successfully";
        } else {
            return "User does not exist";
        }
    }

    public Object getPost(long postID) {
        Optional<SocialMediaEntityPost> postEntityOptional = repositoryPost.findById(postID);
        if (postEntityOptional.isPresent()) {
            SocialMediaEntityPost postEntity = postEntityOptional.get();
            return postEntityToPost(postEntity);
        } else {
            return "Post does not exist";
        }
    }


    public String editPost(long postID, String newPostBody) {
        Optional<SocialMediaEntityPost> postEntityOptional = getPostEntityByID(postID);
        if (postEntityOptional.isPresent()) {
            SocialMediaEntityPost postEntity = postEntityOptional.get();
            updatePostBody(postEntity, newPostBody);
            savePostEntity(postEntity);
            return "Post edited successfully";
        } else {
            return "Post does not exist";
        }
    }

    private Optional<SocialMediaEntityPost> getPostEntityByID(long postID) {
        return repositoryPost.findById(postID);
    }

    private void updatePostBody(SocialMediaEntityPost postEntity, String newPostBody) {
        postEntity.setMainBodyofPost(newPostBody);
    }

    private void savePostEntity(SocialMediaEntityPost postEntity) {
        repositoryPost.save(postEntity);
    }


    public String deletePost(long postId) {
        Optional<SocialMediaEntityPost> postEntityOptional = repositoryPost.findById(postId);
        if (postEntityOptional.isPresent()) {
            repositoryPost.deleteById(postId);
            return "Post deleted";
        } else {
            return "Post does not exist";
        }
    }


    public String login(String email, String password) {
        Optional<SocialMediaEntityUser> userOptional = getUserByEmail(email);
        if (userOptional.isPresent()) {
            SocialMediaEntityUser userEntity = userOptional.get();
            if (isPasswordCorrect(userEntity, password)) {
                return "Login Successful";
            } else {
                return "Username/Password Incorrect";
            }
        } else {
            return "User does not exist";
        }
    }

    private Optional<SocialMediaEntityUser> getUserByEmail(String email) {
        return repositoryUser.findByEmail(email);
    }

    private boolean isPasswordCorrect(SocialMediaEntityUser userEntity, String password) {
        return userEntity.getPasswordofUser().equals(password);
    }



    public String signup(String email, String name, String password) {
        Optional<SocialMediaEntityUser> userOptional = getUserByEmail(email);
        if (userOptional.isPresent()) {
            return "Forbidden, Account already exists";
        } else {
            SocialMediaEntityUser newUserEntity = createUser(email, name, password);
            saveUser(newUserEntity);
            return "Account Creation Successful";
        }
    }


    private SocialMediaEntityUser createUser(String email, String name, String password) {
        return new SocialMediaEntityUser(email, name, password);
    }

    private void saveUser(SocialMediaEntityUser userEntity) {
        repositoryUser.save(userEntity);
    }


    public Object getUserDetails(long userID) {
        Optional<SocialMediaEntityUser> userEntityOptional = repositoryUser.findById(userID);
        if (userEntityOptional.isPresent()) {
            SocialMediaEntityUser userEntity = userEntityOptional.get();

            DetailUser userDetails = new DetailUser();
            userDetails.setUserID(userEntity.getIDofUser());
            userDetails.setName(userEntity.getUserName());
            userDetails.setEmail(userEntity.getUserMailID());
            return userDetails;
        } else {
            return "User does not exist";
        }
    }


    public UserModel userEntityToUser(SocialMediaEntityUser userEntity) {
        if (userEntity == null) {
            return null;
        }

        UserModel user = new UserModel();
        user.setUserID(userEntity.getIDofUser());
        user.setName(userEntity.getUserName());
        user.setEmail(userEntity.getUserMailID());
        user.setPosts(convertPosts(userEntity.getPosts()));

        return user;
    }

    private List<PostModel> convertPosts(List<SocialMediaEntityPost> postEntities) {
        if (postEntities == null) {
            return null;
        }

        List<PostModel> posts = new ArrayList<>();
        for (SocialMediaEntityPost postEntity : postEntities) {
            posts.add(postEntityToPost(postEntity));
        }
        return posts;
    }


    public PostModel postEntityToPost(SocialMediaEntityPost postEntity) {
        PostModel post = new PostModel();
        post.setPostID(postEntity.getIDofPost());
        post.setPostBody(postEntity.getMainBodyofPost());
        post.setDate(formatDate(postEntity.getDateofPost()));
        post.setComments(mapCommentEntitiesToCommentBodies(postEntity.getComments()));
        return post;
    }

    private String formatDate(String date) {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDate.parse(date));
    }

    private List<CommentBody> mapCommentEntitiesToCommentBodies(List<SocialMediaEntityComment> commentEntities) {
        return commentEntities.stream()
                .map(this::commentEntityToComment)
                .collect(Collectors.toList());
    }




    private CommentBody commentEntityToComment(SocialMediaEntityComment commentEntity) {
        CommentBody comment = new CommentBody();
        comment.setCommentID(commentEntity.getIDofComment());
        comment.setCommentBody(commentEntity.getCommentMainBody());
        setCommentCreator(commentEntity, comment);
        return comment;
    }

    private void setCommentCreator(SocialMediaEntityComment commentEntity, CommentBody comment) {
        if (commentEntity.getUser() != null) {
            CommentCreator commentCreator = createCommentCreator(commentEntity.getUser());
            comment.setCommentCreator(commentCreator);
        }
    }

    private CommentCreator createCommentCreator(SocialMediaEntityUser userEntity) {
        CommentCreator commentCreator = new CommentCreator();
        commentCreator.setUserID(userEntity.getIDofUser());
        commentCreator.setName(userEntity.getUserName());
        return commentCreator;
    }



    public List<PostModel> getUserFeed() {
        List<SocialMediaEntityUser> allUsers = repositoryUser.findAll();
        return allUsers.stream()
                .flatMap(userEntity -> userEntity.getPosts().stream())
                .map(this::postEntityToPost)
                .sorted((p1, p2) -> {
                    int dateComparison = p2.getDate().compareTo(p1.getDate());
                    if (dateComparison != 0) {
                        return dateComparison;
                    }
                    return Long.compare(p2.getPostID(), p1.getPostID());
                })
                .collect(Collectors.toList());
    }

    public List<DetailUser> getAllUsers() {
        List<SocialMediaEntityUser> userEntities = repositoryUser.findAll();
        return userEntities.stream()
                .map(this::userEntityToUserDetails)
                .collect(Collectors.toList());
    }

    private DetailUser userEntityToUserDetails(SocialMediaEntityUser userEntity) {
        DetailUser userDetailsDTO = new DetailUser();
        userDetailsDTO.setUserID(userEntity.getIDofUser());
        userDetailsDTO.setName(userEntity.getUserName());
        userDetailsDTO.setEmail(userEntity.getUserMailID());
        return userDetailsDTO;
    }
}
