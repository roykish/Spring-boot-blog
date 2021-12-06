package com.spring.boot.blog.Springbootblog.controller;

import com.spring.boot.blog.Springbootblog.dto.PageResponse;
import com.spring.boot.blog.Springbootblog.dto.PostDto;
import com.spring.boot.blog.Springbootblog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {
    @Autowired
    private PostService postService;
    @PostMapping("/create")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    //get all post
    @GetMapping("/allpost")
    public PageResponse getAllPosts(
            //adding pagination to find all post (1st step, the second step is in the postService interface)
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue= "10", required = false) int pageSize
    ){

        return postService.getAllPosts(pageNo, pageSize);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> findPostById(@PathVariable long id){
        return new ResponseEntity<>(postService.getPostById(id), HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable (name = "id") long id){
        return new ResponseEntity<>(postService.updatePost(postDto,id), HttpStatus.OK) ;
    }

    @DeleteMapping("/delete/{id}")
   public ResponseEntity<String> deletePost(@PathVariable long id){
        postService.deletePostById(id);
        return new ResponseEntity<>("Post has been successfully deleted", HttpStatus.OK);
    }
}
