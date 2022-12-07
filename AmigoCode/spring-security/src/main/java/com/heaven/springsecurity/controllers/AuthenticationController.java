package com.heaven.springsecurity.controllers;

import com.heaven.springsecurity.config.JwtUtils;
import com.heaven.springsecurity.dao.UserDao;
import com.heaven.springsecurity.dto.AuthenticationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final UserDao userDao;

    private final JwtUtils utils;

    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(@RequestBody AuthenticationRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword())
        );
        final UserDetails user = userDao.findByEmail(request.getEmail());
        if(user!=null){
            return ResponseEntity.ok(utils.generateToken(user));
        }
        return ResponseEntity.status(400).body("Invalid email or password");
    }
}
