package com.spring.boot.blog.Springbootblog.serviceImplimentation;

import com.spring.boot.blog.Springbootblog.dao.PostRepository;
import com.spring.boot.blog.Springbootblog.dto.PageResponse;
import com.spring.boot.blog.Springbootblog.dto.PostDto;
import com.spring.boot.blog.Springbootblog.entity.Post;
import com.spring.boot.blog.Springbootblog.exceptions.ResourceNotFoundException;
import com.spring.boot.blog.Springbootblog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;

    @Override
    public PostDto createPost(PostDto postDto) {
        //calling an external conversion method to convert post dto to post entity.
        Post post = mapToEntity(postDto);
        Post newPost = postRepository.save(post);

        //calling an external conversion method to convert Post entity to Post Dto
        //this method is just for the response to the client as a dto object.
        PostDto postResponse = mapToDto(newPost);
        return postResponse;
    }

    //convert Post entity to PostDto in a separate private method
    private PostDto mapToDto(Post post) {
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setDescription(post.getDescription());
        postDto.setContent(post.getContent());
        return postDto;
    }

    //converting dto to entity
    private Post mapToEntity(PostDto postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        return post;
    }

    @Override
    public PageResponse getAllPosts(int pageNo, int pageSize) {

        /*For pagination, there are two kind of Pageable, one is java.awt.print.Pageable and the
         other one is org.springframework.data.domain.Pageable. We need to use the second one.*/

        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Post> posts = postRepository.findAll(pageable);

        //Everytime there is a page, that page need to fetched by .getContent() method and pass it to the stream.
        List<Post> listOfPosts = posts.getContent();

        List<PostDto> content = listOfPosts.stream().map(this::mapToDto).collect(Collectors.toList());
        PageResponse pageResponse = new PageResponse();
        pageResponse.setContent(content);
        pageResponse.setPageNo(posts.getNumber());
        pageResponse.setPageSize(posts.getSize());
        pageResponse.setTotalElements(posts.getTotalElements());
        pageResponse.setTotalPages(posts.getTotalPages());
        pageResponse.setLast(posts.isLast());
        return pageResponse;
    }

    @Override
    public PostDto getPostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        return mapToDto(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, long id) {
        //get post by id
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        Post updatedPost = postRepository.save(post);
        //convert to dto and send a response to the client
        return mapToDto(updatedPost);

        /*
        -------------------------------------------------
        elaboration of line 75
        -------------------------------------------------
        PostDto updatedResponse = mapToDto(updatedPost);
        return updatedResponse;

         */
    }

    @Override
    public void deletePostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        postRepository.delete(post);
    }


}
