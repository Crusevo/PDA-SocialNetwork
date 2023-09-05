package com.example.Twitter.Service;

import com.example.Twitter.Model.Comment;
import com.example.Twitter.Model.Post;
import com.example.Twitter.Model.User;
import com.example.Twitter.Repository.CommentRepository;
import com.example.Twitter.Repository.PostRepository;
import com.example.Twitter.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {


    @Autowired PostRepository postRepository;
    @Autowired UserRepository userRepository;
    @Autowired CommentRepository commentRepository;



    public List<Post> getAllPosts(){
        return postRepository.findAll();
    }

    public Post addPost(Post post) {
        return postRepository.save(post);
    }

    public void deletePost(long postId){

        postRepository.deleteById(postId);

    }

    public List<Post> getPostsByUserId(long userId) {
        User userFromDatabase = userRepository.findById(userId).orElseThrow();
        return userFromDatabase.getPosts();
    }

    public Post giveLike(long postId) {

        Post postFromDatabase = postRepository.findById(postId).orElseThrow();

        int likes = postFromDatabase.getLikes();

        int giveLike = likes + 1;

        postFromDatabase.setLikes(giveLike);

        return postRepository.save(postFromDatabase);


    }

    public Comment giveComment(Comment comment, long userId, long postId) {

        User userFromDatabase = userRepository.findById(userId).orElseThrow();
        Post postFromDatabase = postRepository.findById(postId).orElseThrow();

        List<Comment> userFromDatabaseComments = userFromDatabase.getComments();
        List<Comment> postFromDatabaseComments = postFromDatabase.getComments();

        comment.setUser(userFromDatabase);
        comment.setPost(postFromDatabase);

        commentRepository.save(comment);

        userFromDatabaseComments.add(comment);
        postFromDatabaseComments.add(comment);

        userRepository.save(userFromDatabase);
        postRepository.save(postFromDatabase);

        return comment;
    }
}
