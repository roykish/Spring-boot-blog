package com.spring.boot.blog.Springbootblog.serviceImplimentation;

import com.spring.boot.blog.Springbootblog.dao.CommentRepository;
import com.spring.boot.blog.Springbootblog.dao.PostRepository;
import com.spring.boot.blog.Springbootblog.dto.CommentDto;
import com.spring.boot.blog.Springbootblog.entity.Comment;
import com.spring.boot.blog.Springbootblog.entity.Post;
import com.spring.boot.blog.Springbootblog.exceptions.BlogApiException;
import com.spring.boot.blog.Springbootblog.exceptions.ResourceNotFoundException;
import com.spring.boot.blog.Springbootblog.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ModelMapper modelMapper;

    //updating a comment with specific post ID, the comment ID and the updated comment DTO.
    @Override
    public CommentDto updateComment(long postId, long commentId, CommentDto commentDto) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        Comment comments = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));
        if (!(comments.getPosts().getId() == post.getId())) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }
        comments.setName(commentDto.getName());
        comments.setEmail(commentDto.getEmail());
        comments.setMsgBody(comments.getMsgBody());
        Comment updatedComment = commentRepository.save(comments);
        return mapToDto(updatedComment);
    }

    //deleting a comment by postID and the commentID
    @Override
    public void deleteCommentById(long postId, Long commentId) {

        Comment comments = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        if (!(comments.getPosts().getId() == post.getId())) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }
        commentRepository.delete(comments);
    }

    //creating a comment for a specific postID.
    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        Comment comment = mapToEntity(commentDto);
        comment.setPosts(post);
        Comment newComment = commentRepository.save(comment);

        //sending a response to the client through the dto object.
        CommentDto commentResponse = mapToDto(newComment);
        return commentResponse;
    }

    //find all comments for a specific post.
    @Override
    public List<CommentDto> getCommentsByPostId(long postId) {
        List<Comment> comments = commentRepository.findByPostsId(postId);
        return comments.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());
    }

    //Find a specific comment for a specific post ID.
    @Override
    public CommentDto getCommentById(Long postId, Long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        Comment comments = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));
        //Checking whether the given commentID is associated with the given postID
        if (!(comments.getPosts().getId() == post.getId())) {
            //if not, then throw a custom Exception BlogApiException
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }
        //returning a DTO response to the client.
        return mapToDto(comments);
    }

    //converting entity to dto
    private CommentDto mapToDto(Comment comment) {
        /*
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setEmail(comment.getEmail());
        commentDto.setName(comment.getName());
        commentDto.setMsgBody(comment.getMsgBody());

        As we are using ModelMapper, we can use this library API to convert the entity to DTO or DTO to entity
        That is why from line 92-96 is replaced by line bellow.
         */
        CommentDto commentDto = modelMapper.map(comment, CommentDto.class);
        return commentDto;
    }

    //converting dto to entity
    private Comment mapToEntity(CommentDto commentDto) {
        /*
        Comment comment = new Comment();
        comment.setId(commentDto.getId());
        comment.setEmail(commentDto.getEmail());
        comment.setName(commentDto.getName());
        comment.setMsgBody(commentDto.getMsgBody());

        As we are using ModelMapper, we can use this library API to convert the entity to DTO or DTO to entity
        That is why from line 108-112 is replaced by line bellow.
         */
        Comment comment = modelMapper.map(commentDto, Comment.class);
        return comment;
    }
}
