package com.heaven.springjwt.repository;

import com.heaven.springjwt.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository extends JpaRepository<User,Long> {
    User findUserByUserName(String username);
}
