package com.spring.boot.blog.Springbootblog.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collector;

@Data
public class PostDto {
    private Long id;
    private String title;
    private String description;
    private String content;

}
