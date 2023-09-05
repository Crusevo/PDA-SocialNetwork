package com.example.Twitter.Repository;

import com.example.Twitter.Model.Post;
import com.example.Twitter.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {

    @Query
    List<Post> findByPostTitle(String postTitle);

    @Query
    List<Post> findByUserId(long id);


}
