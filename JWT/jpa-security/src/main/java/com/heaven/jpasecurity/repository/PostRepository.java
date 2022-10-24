package com.heaven.jpasecurity.repository;

import com.heaven.jpasecurity.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}
