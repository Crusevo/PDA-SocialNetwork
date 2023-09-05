package com.example.Twitter.RestController;

import com.example.Twitter.Model.Comment;
import com.example.Twitter.Model.Post;
import com.example.Twitter.Model.User;
import com.example.Twitter.Service.PostService;
import com.example.Twitter.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostRestController {

    @Autowired
    PostService postService;


    @GetMapping("/posts/all/")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Post> getAllPosts(){
        return postService.getAllPosts();
    }

    @GetMapping("/posts/{userId}/")
    public List<Post> getPostsByUserId(@PathVariable ("userId") long userId){
        return postService.getPostsByUserId(userId);
    }

    @DeleteMapping("/posts/delete/{postId}")
    public void deletePost(@PathVariable("postId") long postId){
        postService.deletePost(postId);
    }

    @PutMapping("/posts/givelike/{postId}")
    public Post giveLike(@PathVariable ("postId") long postId){
        return postService.giveLike(postId);
    }

    @PutMapping("/posts/givecomment/{userId}/{postId}")
    public Comment giveComment(@RequestBody Comment comment, @PathVariable ("userId") long userId, @PathVariable ("postId") long postId){
        return postService.giveComment(comment,userId,postId);
    }




}
