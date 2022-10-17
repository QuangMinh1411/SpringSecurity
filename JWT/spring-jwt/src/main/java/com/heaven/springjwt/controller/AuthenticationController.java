package com.heaven.springjwt.controller;

import com.heaven.springjwt.config.JWTTokenHelper;
import com.heaven.springjwt.entities.User;
import com.heaven.springjwt.request.AuthenticationRequest;
import com.heaven.springjwt.response.LoginResponse;
import com.heaven.springjwt.response.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.spec.InvalidKeySpecException;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTTokenHelper jwtTokenHelper;
    @Autowired
    private UserDetailsService userDetailsService;
    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest request) throws InvalidKeySpecException, NoSuchAlgorithmException {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = (User) authentication.getPrincipal();
        String jwtToken = jwtTokenHelper.generateToken(user.getUsername());
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        return ResponseEntity.ok(loginResponse);
    }

    @GetMapping("/auth/userinfo")
    public ResponseEntity<?> getUserInfo(Principal user){
        User userObj = (User) userDetailsService.loadUserByUsername(user.getName());
        UserInfo userInfo = new UserInfo();
        userInfo.setFirstName(userObj.getFirstName());
        userInfo.setLastName(userObj.getLastName());
        userInfo.setUserName(userObj.getUsername());
        userInfo.setRoles(userObj.getAuthorities().toArray());
        return ResponseEntity.ok(userInfo);
    }
}
