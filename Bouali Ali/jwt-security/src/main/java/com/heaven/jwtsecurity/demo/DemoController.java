package com.heaven.jwtsecurity.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/demo")
public class DemoController {
    @GetMapping
    public ResponseEntity<String> hello(Principal principal){
        return ResponseEntity.ok("Hello from secured endpoint" + principal.getName());
    }
}
