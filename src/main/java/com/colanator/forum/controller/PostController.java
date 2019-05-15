package com.colanator.forum.controller;

import com.colanator.forum.model.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PostController {

	@Autowired
	private PostRepository repository;

	@RequestMapping("/")
	public String getPostByName(@RequestParam(value="author") String author) {

		final StringBuilder builder = new StringBuilder();

		repository.findByAuthor(author).forEach(post -> {
			builder.append(post.toString());
		});

		String concatenatedString = builder.toString();

		return concatenatedString;
	}
}
