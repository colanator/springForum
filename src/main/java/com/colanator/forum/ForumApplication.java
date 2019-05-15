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
			// save a couple of customers
			repository.save(new Post("colanator", "MyPost1", "The Body1"));
			repository.save(new Post("aapo", "MyPost2", "The Body2"));
			repository.save(new Post("troll", "MyPost3", "The Body3"));

			// fetch all customers
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (Post post : repository.findAll()) {
				log.info(post.toString());
			}
			log.info("");

			// fetch an individual customer by ID
			repository.findById(1L)
					.ifPresent(post -> {
						log.info("Post found with findById(1L):");
						log.info("--------------------------------");
						log.info(post.toString());
						log.info("");
					});

			// fetch customers by last name
			log.info("Post found with findByAuthor(\"colanator\"):");
			log.info("--------------------------------------------");
			repository.findByAuthor("colanator").forEach(bauer -> {
				log.info(bauer.toString());
			});
			// for (Post bauer : repository.findByLastName("Bauer")) {
			// 	log.info(bauer.toString());
			// }
			log.info("");
		};
	}

}
