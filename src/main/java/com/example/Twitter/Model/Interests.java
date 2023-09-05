package com.example.Twitter.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
public class Interests {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long interestsId;

    @NotNull()
    @NotBlank()
    private String interestsName;

    @JsonIgnore
    @ManyToMany(mappedBy = "interestsList")
    private List<User> userList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Interests interests = (Interests) o;
        return Objects.equals(interestsName, interests.interestsName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(interestsName);
    }
}
