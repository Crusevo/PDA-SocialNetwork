package com.example.Twitter.Repository;

import com.example.Twitter.Model.Comment;
import com.example.Twitter.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {




}
