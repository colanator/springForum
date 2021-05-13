package com.colanator.forum;

import com.colanator.forum.model.Board;
import com.colanator.forum.model.BoardRepository;
import com.colanator.forum.service.ContentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ForumApplication {

    public static void main(String[] args) {
        SpringApplication.run(ForumApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(ContentService contentService, BoardRepository boardRepository) {
        return (args) -> {

            // save a post and board for testing
            Board board = new Board("Forum board");

            boardRepository.save(board);
            contentService.addPostToBoard(board.getId(), "The Title", "The Body", "The Author");

            // print out saved posts
            contentService.listAllPostsOnBoard(board.getId()).forEach(it -> System.out.println(it.toString()));
        };
    }
}
