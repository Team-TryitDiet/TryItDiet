package com.example.tryitdiet.services;

import com.example.tryitdiet.models.User;
import com.example.tryitdiet.models.UserWithRoles;
import com.example.tryitdiet.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsLoader implements UserDetailsService {

    private final UserRepository users; // gives us access to the ability to grab/manipulate User object(s)

    public UserDetailsLoader(UserRepository users) {
        this.users = users;
    }

    // UserDetails interface allows us to get password / username/ check if account is expired, locked, or credentials expired
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = users.findByUsername(username);

        // check to see if we actually found a user or not
        if (user == null) {
            // Now our console log output will be more detailed, in order for us to find the problem more quickly,
            // because we know exactly that this had to do with not finding that username in the DB
            // this tells our Java app to throw the exception immediately
            throw new UsernameNotFoundException("User was not found for username: " + username);
        }

        return new UserWithRoles(user); // this will return the result of sending the 'user' object INTO a
        // 'UserWithRoles' constructor, and getting the output from using that constructor
    }
}
