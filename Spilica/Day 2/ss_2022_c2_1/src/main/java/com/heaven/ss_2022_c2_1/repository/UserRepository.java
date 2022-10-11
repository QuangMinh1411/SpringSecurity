package com.heaven.ss_2022_c2_1.repository;

import com.heaven.ss_2022_c2_1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findUserByName(String username);
}
