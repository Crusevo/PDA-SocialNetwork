package com.example.Twitter.Security;

import com.example.Twitter.Model.User;
import com.example.Twitter.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        List<User> byUserName = userRepository.findByUserName(username);

        if(byUserName.isEmpty()) throw new UsernameNotFoundException("Nie znaleziono użytkownika o tej nazwie");

        return new MyUserDetails(byUserName.get(0));
    }
}
