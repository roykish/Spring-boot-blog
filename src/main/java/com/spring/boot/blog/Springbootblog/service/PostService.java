package com.spring.boot.blog.Springbootblog.service;

import com.spring.boot.blog.Springbootblog.dto.PostDto;

import java.util.List;


public interface PostService {
    PostDto createPost(PostDto postDto);
    List<PostDto> getAllPosts();
    PostDto getPostById(long id);
    PostDto updatePost(PostDto postDto, long id );
    void deletePostById(long id);
}
