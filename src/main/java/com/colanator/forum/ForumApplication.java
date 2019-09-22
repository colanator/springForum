package com.colanator.forum;

import com.colanator.forum.model.*;
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
	public CommandLineRunner demo(PostRepository postRepository, BoardRepository boardRepository) {
		return (args) -> {
			// save a post and board for testing
			Post post = new Post("The Author", "The Title", "The Body");
			post.getReplies().add(
					new Reply("Reply Author", "Reply Body1")
			);
			post.getReplies().add(
					new Reply("Reply Author", "Reply Body2")
			);
			Board board = new Board("Forum board");
			board.getPosts().add(post);
			boardRepository.save(board);
			postRepository.save(post);
			// print out saved posts
			postRepository.findAll().forEach( it -> System.out.println(it.toString()) );
			boardRepository.findAll().forEach( it -> System.out.println(it.toString()) );
		};
	}

}
