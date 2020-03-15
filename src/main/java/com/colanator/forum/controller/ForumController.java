package com.colanator.forum.controller;

import com.colanator.forum.model.Post;
import com.colanator.forum.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class ForumController {

	private ContentService contentService;

	@Autowired
	public ForumController(ContentService contentService){
		this.contentService = contentService;
	}

	@GetMapping("/posts")
	public ResponseEntity<List<Post>> getPostsOnDefaultBoard() {
		List<Post> posts = contentService.listAllPostsOnBoard((long) 1);
		if(!posts.isEmpty()){
			return ResponseEntity.ok(posts);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@RequestMapping(value = "/posts/{id}", method = GET)
	@ResponseBody
	public ResponseEntity<Post> getPost(@PathVariable Long id) {
		Post post;
		Optional<Post> optionalPost = contentService.getPost(id);
		if(optionalPost.isPresent()){
			post = optionalPost.get();
			return ResponseEntity.ok(post);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
