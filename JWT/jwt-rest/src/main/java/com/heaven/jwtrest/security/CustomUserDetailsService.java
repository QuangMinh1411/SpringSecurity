package com.heaven.jwtrest.security;

import com.heaven.jwtrest.entity.User;
import com.heaven.jwtrest.repository.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepo repo;

    public CustomUserDetailsService(UserRepo repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = repo.findByEmail(email);
        if(user.isPresent()){
            return new SecurityUser(user.get());
        }else throw new UsernameNotFoundException("User with email: " + email + " not found");
    }
}
