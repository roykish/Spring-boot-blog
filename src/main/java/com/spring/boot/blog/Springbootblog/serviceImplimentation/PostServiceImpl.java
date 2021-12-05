package com.spring.boot.blog.Springbootblog.serviceImplimentation;

import com.spring.boot.blog.Springbootblog.dao.PostRepository;
import com.spring.boot.blog.Springbootblog.dto.PostDto;
import com.spring.boot.blog.Springbootblog.entity.Post;
import com.spring.boot.blog.Springbootblog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;
    @Override
    public PostDto createPost(PostDto postDto) {
        //converting dto to entity
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        Post newPost = postRepository.save(post);
        //converting entity to dto
        PostDto postResponse = new PostDto();
        postResponse.setId(newPost.getId());
        postResponse.setTitle(newPost.getTitle());
        postResponse.setDescription(newPost.getDescription());
        postResponse.setContent(newPost.getContent());
        return postResponse;
    }
}
