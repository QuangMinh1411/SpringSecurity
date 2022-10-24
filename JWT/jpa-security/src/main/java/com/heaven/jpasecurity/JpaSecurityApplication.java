package com.heaven.jpasecurity;

import com.heaven.jpasecurity.model.Post;
import com.heaven.jpasecurity.model.User;
import com.heaven.jpasecurity.repository.PostRepository;
import com.heaven.jpasecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class JpaSecurityApplication {


    public static void main(String[] args) {
        SpringApplication.run(JpaSecurityApplication.class, args);
    }
    @Bean
    public CommandLineRunner commandLineRunner(PostRepository postRepository, UserRepository userRepository, PasswordEncoder encoder){
        return args -> {
            userRepository.save(new User("vegan",encoder.encode("1234"),"ROLE_USER"));
            userRepository.save((new User("quang",encoder.encode("1234"),"ROLE_ADMIN,ROLE_USER")));
            postRepository.save(new Post("Hello","hello-world","Welcome to my blog","Sir Quang"));

        };
    }
}
