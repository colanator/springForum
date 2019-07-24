package com.colanator.forum;

import com.colanator.forum.model.Post;
import com.colanator.forum.model.PostRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ForumApplication {

	private static final Logger log = LoggerFactory.getLogger(ForumApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ForumApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(PostRepository repository) {
		return (args) -> {
			// save a couple of posts for testing
			repository.save(new Post("colanator", "MyPost1", "The Body1"));
			repository.save(new Post("aapo", "MyPost2", "The Body2"));
			repository.save(new Post("troll", "MyPost3", "The Body3"));
			repository.save(new Post("anon", "MyPost4", "The Body4"));
		};
	}

}
