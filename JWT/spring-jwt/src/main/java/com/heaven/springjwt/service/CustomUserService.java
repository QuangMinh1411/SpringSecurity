package com.heaven.springjwt.service;

import com.heaven.springjwt.entities.User;
import com.heaven.springjwt.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserService implements UserDetailsService {
    @Autowired
    private UserDetailsRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =repo.findUserByUserName(username);
        if(user==null){
            throw new UsernameNotFoundException("User not found with: " +username);
        }
        return user;
    }
}
