package com.colanator.forum.controller;

import com.colanator.forum.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PostController {

	private PostService service;

	@Autowired
	public PostController(PostService service) {
		this.service = service;
	}

	@GetMapping("/")
	public String getPostByName(Model model, @RequestParam(value = "author", required = false, defaultValue = "anon") String author) {
		model.addAttribute("author", service.findByAuthor(author));
		return "post";
	}
}
