package com.example.Twitter.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.lang.NonNull;
import org.springframework.lang.NonNullApi;
import org.springframework.lang.NonNullFields;
import org.springframework.lang.Nullable;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @NotNull()
    @NotBlank()
    private String userName;

    @NotNull()
    @NotBlank()
    private String lastName;

    @NotNull()
    @NotBlank()
    @Email()
    private String email;

    @NotNull()
    @NotBlank()
    private String password;

    @Max(99)
    @Min(1)
    private long age;

    @NotNull()
    @NotBlank()
    private String image = "https://vader.joemonster.org/upload/zhr/444925ba2791c3avatar_spock_2.jpg";

    private String address;

    @ManyToMany
    private List<User> favoriteUsers;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "User_Shared_Posts", joinColumns = @JoinColumn(name = "User_Id"),
            inverseJoinColumns = @JoinColumn(name = "Shared_Post_Id")
    )
    private List <Post> sharedPostList;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "User_Posts", joinColumns = @JoinColumn(name = "User_Id"),
            inverseJoinColumns = @JoinColumn(name = "Post_Id")
    )
    private List<Post> posts;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "User_Comments", joinColumns = @JoinColumn(name = "User_Id"),
            inverseJoinColumns = @JoinColumn(name = "Comment_Id")
    )
    private List<Comment> comments;

    @ManyToMany
    @JoinTable(name = "User_Interests", joinColumns = @JoinColumn(name = "User_Id"),
            inverseJoinColumns = @JoinColumn(name = "Interests_Id")
    )
    private List<Interests> interestsList;

    @ManyToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    @JoinTable(name = "User_Role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> userRoles;


}
