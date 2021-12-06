package com.spring.boot.blog.Springbootblog.controller;

import com.spring.boot.blog.Springbootblog.dto.CommentDto;
import com.spring.boot.blog.Springbootblog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @PostMapping("/{id}/create")
    public ResponseEntity<CommentDto> createComment(@PathVariable(value = "id") long id, @RequestBody CommentDto commentDto){
       return new ResponseEntity<>(commentService.createComment(id,commentDto), HttpStatus.CREATED);
    }

    @GetMapping("/comments/{postId}")
    public List<CommentDto> showComments(@PathVariable(value = "postId") long postId){
        return commentService.getCommentsByPostId(postId);
    }
}
