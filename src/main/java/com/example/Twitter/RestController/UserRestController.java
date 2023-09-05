package com.example.Twitter.RestController;

import com.example.Twitter.Model.Interests;
import com.example.Twitter.Model.Post;
import com.example.Twitter.Model.User;
import com.example.Twitter.Service.PostService;
import com.example.Twitter.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserRestController {

    @Autowired UserService userService;



    @GetMapping("/users/all/")
    @PreAuthorize("hasAuthority('USER')")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}/")
    public User getUserById(@PathVariable ("id") long id){
        return userService.getUserById(id);
    }

    @PostMapping("/users/adduser/")
    public User addUser (@RequestBody @Valid User user){
        return userService.addUser(user);
    }

    @DeleteMapping("/users/delete/{id}")
    public void deleteUser(@PathVariable("id") long id){
        userService.deleteUser(id);
    }

    @PatchMapping("/users/addpost/{userId}")
    public User createPost(@RequestBody @Valid Post post,  @PathVariable ("userId") long id){
        return userService.userAddPost(post,id);
    }

    @PatchMapping ("/users/addProfileImage/{userId}/{image}")
    public void addProfileImage(@PathVariable ("userId") long id, @PathVariable ("image") String image){
       userService.addProfileImage(id, image);
    }

    @PutMapping("/users/sharePost/{userId}/{postId}")
    public User sharePost (@PathVariable ("userId") long userId, @PathVariable ("postId") long postId){
        return userService.sharePost(userId, postId);
    }

    @PutMapping("/users/addToFriend/{userWchichAddToFavoriteId}/{userId}")
    public User addToFriend (@PathVariable ("userWchichAddToFavoriteId") long userWchichAddToFavoriteId, @PathVariable ("userId") long userId ){
        return userService.addToFriend(userWchichAddToFavoriteId,userId);
    }

    @GetMapping("/users/findByAddress/{address}")
    public List<User> findByAdress (@PathVariable ("address") String address){
        return userService.findByAdress(address);
    }

    @PutMapping("/users/addInterests/{userId}")
    public User addInterests (@RequestBody Interests interests, @PathVariable ("userId") long userId){
        return userService.addInterests(interests,userId);
    }








}
