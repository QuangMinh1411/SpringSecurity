package com.heaven.jwtrest.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/")

public class Demo {

    @GetMapping("/demo")
    public ResponseEntity<String> sayHello(Principal principal) {

        return ResponseEntity.ok("Hello " + principal.getName());
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> adminResource(){
        return ResponseEntity.ok("Admin content");
    }


}
