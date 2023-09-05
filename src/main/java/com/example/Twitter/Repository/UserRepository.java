package com.example.Twitter.Repository;

import com.example.Twitter.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query
    List<User> findByUserName(String userName);

    @Query
    List<User> findByLastName(String lastName);

    @Query
    List<User> findByAddress(String address);




}
