package com.spring.boot.blog.Springbootblog.serviceImplimentation;

import com.spring.boot.blog.Springbootblog.dao.CommentRepository;
import com.spring.boot.blog.Springbootblog.dao.PostRepository;
import com.spring.boot.blog.Springbootblog.dto.CommentDto;
import com.spring.boot.blog.Springbootblog.entity.Comment;
import com.spring.boot.blog.Springbootblog.entity.Post;
import com.spring.boot.blog.Springbootblog.exceptions.ResourceNotFoundException;
import com.spring.boot.blog.Springbootblog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;
    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "id", postId));
        Comment comment = mapToEntity(commentDto);
        comment.setPosts(post);
        Comment newComment = commentRepository.save(comment);

        //sending a response to the client through the dto object.
        CommentDto commentResponse = mapToDto(newComment);
        return commentResponse;
    }

    //converting entity to dto
    private CommentDto mapToDto(Comment comment){
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setEmail(comment.getEmail());
        commentDto.setName(comment.getName());
        commentDto.setMsgBody(comment.getMsgBody());
        return commentDto;
    }
    //converting dto to entity
    private Comment mapToEntity(CommentDto commentDto){
        Comment comment = new Comment();
        comment.setId(commentDto.getId());
        comment.setEmail(commentDto.getEmail());
        comment.setName(commentDto.getName());
        comment.setMsgBody(commentDto.getMsgBody());
        return comment;
    }
}