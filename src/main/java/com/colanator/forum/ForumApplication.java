package com.colanator.forum;

import com.colanator.forum.model.Post;
import com.colanator.forum.model.PostRepository;
import com.colanator.forum.model.Reply;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ForumApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForumApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(PostRepository repository) {
		return (args) -> {
			// save a post for testing
			Post post = new Post("The Author", "The Title", "The Body");
			post.getReplies().add(
					new Reply("Reply Author", "Reply Body1")
			);
			post.getReplies().add(
					new Reply("Reply Author", "Reply Body2")
			);
			repository.save(post);
			// print out saved posts
			repository.findAll().forEach( it -> System.out.println(it.toString()) );
		};
	}

}
