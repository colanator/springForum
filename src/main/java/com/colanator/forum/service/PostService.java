package com.colanator.forum.service;

import com.colanator.forum.model.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostService {

	private PostRepository repository;

	@Autowired
	public PostService(PostRepository repository) {
		this.repository = repository;
	}

	public String findPostsByAuthor(String author){
		final StringBuilder builder = new StringBuilder();

		repository.findByAuthor(author).forEach(post -> {
			builder.append(post.toString());
		});

		return builder.toString();
	}
}
