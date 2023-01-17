package com.heaven.jwtrest;

import com.heaven.jwtrest.entity.Role;
import com.heaven.jwtrest.entity.User;
import com.heaven.jwtrest.repository.UserRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class JwtRestApplication implements CommandLineRunner {
    private final PasswordEncoder encoder;
    private final UserRepo repo;

    public JwtRestApplication(PasswordEncoder encoder, UserRepo repo) {
        this.encoder = encoder;
        this.repo = repo;
    }

    public static void main(String[] args) {
        SpringApplication.run(JwtRestApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        User admin = new User(1,"quang","minh","heavenlight@yahoo.com",encoder.encode("admin"), Role.ADMIN);
        repo.save(admin);
    }
}
