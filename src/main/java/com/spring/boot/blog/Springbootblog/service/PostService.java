package com.spring.boot.blog.Springbootblog.service;

import com.spring.boot.blog.Springbootblog.dto.PageResponse;
import com.spring.boot.blog.Springbootblog.dto.PostDto;

import java.util.List;


public interface PostService {
    PostDto createPost(PostDto postDto);
    // For the pagination we need to send two parameters in the method. 3rd step in the implementation of this method.
    PageResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);
    PostDto getPostById(long id);
    PostDto updatePost(PostDto postDto, long id );
    void deletePostById(long id);
}
