package com.colanator.forum.controller;

import com.colanator.forum.dto.PostDTO;
import com.colanator.forum.model.Post;
import com.colanator.forum.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class ForumController {

	private final ContentService contentService;

	@Autowired
	public ForumController(ContentService contentService) {
		this.contentService = contentService;
	}

	@RequestMapping(value = "/", method = GET)
	@ResponseBody
	public ResponseEntity<List<Post>> getPostsOnDefaultBoard() {
		List<Post> posts = contentService.listAllPostsOnBoard((long) 1);
		if(!posts.isEmpty()){
			return ResponseEntity.ok(posts);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@RequestMapping(value = "/boards/{id}", method = GET)
	@ResponseBody
	public ResponseEntity<List<Post>> getPostsOnBoard(@PathVariable Long id) {
		List<Post> posts = contentService.listAllPostsOnBoard(id);
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

	@RequestMapping(value = "/posts", method = POST)
	public ResponseEntity<String> createPost(@RequestBody PostDTO postDto) {
		ResponseEntity<String> result;
		Long boardId = postDto.getBoardId();
		String title = postDto.getTitle();
		String body = postDto.getBody();
		String author = postDto.getAuthor();

		boolean success = contentService.addPostToBoard(boardId, title, body, author);
		if (success) {
			result = ResponseEntity.ok().build();
		} else {
			result = ResponseEntity.notFound().build();
		}
		return result;
	}
}
