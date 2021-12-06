package com.spring.boot.blog.Springbootblog.service;

import com.spring.boot.blog.Springbootblog.dto.CommentDto;
import com.spring.boot.blog.Springbootblog.entity.Comment;

public interface CommentService {
    CommentDto createComment(long postId, CommentDto commentDto);
}
