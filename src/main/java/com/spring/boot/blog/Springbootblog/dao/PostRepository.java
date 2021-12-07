package com.spring.boot.blog.Springbootblog.dao;

import com.spring.boot.blog.Springbootblog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
