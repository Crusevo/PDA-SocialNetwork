package com.example.Twitter.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.lang.NonNull;

import java.util.List;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long postId;

    @NotNull()
    @NotBlank()
    private String postTitle;

    @NotNull()
    @NotBlank()
    private String postContent;

    private int likes =0;


    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "userWchichSharedPost_id")
    private User userWchichSharedPost;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Post_Comments", joinColumns = @JoinColumn(name = "Post_Id"),
            inverseJoinColumns = @JoinColumn(name = "Comment_Id")
    )
    private List<Comment> comments;



}
