package com.heaven.springsecurity.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
@Repository
public class UserDao {

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public final List<UserDetails> APPLICATION_USERS;

    public UserDao() {
        APPLICATION_USERS = Arrays.asList(
                new User(
                        "quang@gmail.com",
                        passwordEncoder.encode("password"),
                        Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"))
                ),
                new User(
                        "user@gmail.com",
                        passwordEncoder.encode("password"),
                        Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"))
                )
        );
    }

    public UserDetails findByEmail(String email){
        return APPLICATION_USERS.stream()
                .filter(u->u.getUsername().equals(email))
                .findFirst()
                .orElseThrow(()->new UsernameNotFoundException("Email: " +email +" not found"));
    }


}
