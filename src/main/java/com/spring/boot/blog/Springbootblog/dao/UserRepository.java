package com.spring.boot.blog.Springbootblog.dao;

import com.spring.boot.blog.Springbootblog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsernameOrEmail(String username, String email);
    Optional<User> findByUsername(String username);
    Boolean isExistByUsername(String username);
    Boolean isExistByEmail(String email);
}
