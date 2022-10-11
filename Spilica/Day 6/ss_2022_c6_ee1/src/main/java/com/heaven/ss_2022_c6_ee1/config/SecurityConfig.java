package com.heaven.ss_2022_c6_ee1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration

public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService(){
        var uds = new InMemoryUserDetailsManager();
        var u1 = User.withUsername("bill")
                .password(passwordEncoder().encode("12345"))
                .authorities("read")
                .build();
        var u2 = User.withUsername("john")
                .password(passwordEncoder().encode("12345"))
                .authorities("write","delete")
                .build();
        uds.createUser(u1);
        uds.createUser(u2);
        return uds;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.httpBasic()
                .and().authorizeRequests()
                .mvcMatchers("/demo/**").hasAuthority("read")
                .mvcMatchers(HttpMethod.GET,"/demo/**").hasAuthority("read")
                .anyRequest().authenticated()

//                .mvcMatchers("/test/test1").authenticated()

                .anyRequest().permitAll()
                .and().csrf().disable() //DONT DO IT IN REAL APP
                .build();
    }
}
