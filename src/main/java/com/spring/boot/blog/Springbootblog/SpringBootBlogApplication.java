package com.spring.boot.blog.Springbootblog;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootBlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootBlogApplication.class, args);
	}
	@Bean
	public ModelMapper modelmapper(){
		return new ModelMapper();
	}

}
