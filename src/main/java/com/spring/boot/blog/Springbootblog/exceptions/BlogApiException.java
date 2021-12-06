package com.spring.boot.blog.Springbootblog.exceptions;

import org.springframework.http.HttpStatus;

public class BlogApiException extends RuntimeException {
    private HttpStatus status;
    private String message;
}
