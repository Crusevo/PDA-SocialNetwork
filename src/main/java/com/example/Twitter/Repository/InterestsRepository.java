package com.example.Twitter.Repository;

import com.example.Twitter.Model.Comment;
import com.example.Twitter.Model.Interests;
import com.example.Twitter.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterestsRepository extends JpaRepository<Interests,Long> {

    @Query
    List<Interests> findByInterestsName(String interestsName);


}
