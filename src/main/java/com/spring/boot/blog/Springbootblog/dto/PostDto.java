package com.spring.boot.blog.Springbootblog.dto;

import lombok.Data;

import java.util.List;
import java.util.stream.Collector;

@Data
public class PostDto {
    private Long id;
    private String title;
    private String description;
    private String content;

}
