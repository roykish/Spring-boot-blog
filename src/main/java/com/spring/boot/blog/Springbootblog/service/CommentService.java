package com.spring.boot.blog.Springbootblog.service;

import com.spring.boot.blog.Springbootblog.dto.CommentDto;
import com.spring.boot.blog.Springbootblog.entity.Comment;

import java.util.List;

public interface CommentService {
    CommentDto createComment(long postId, CommentDto commentDto);
    List<CommentDto> getCommentsByPostId(long postId);
    CommentDto getCommentById(Long postId, Long commentId);
    CommentDto updateComment(long postId, long commentId, CommentDto commentDto);
    void deleteCommentById(long postId, Long commentId);
}
