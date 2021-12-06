package com.spring.boot.blog.Springbootblog.dao;

import com.spring.boot.blog.Springbootblog.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
