package com.spring.boot.blog.Springbootblog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
/*
Error detail is a class where we can handle an exception, pass the exception details as a message, and can add more scopes.
This is will generate an exception object in globalExceptionHandlerClass.
*/
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails {
    private Date timestamp;
    private String message;
    private String details;
}
