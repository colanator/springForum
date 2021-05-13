package com.colanator.forum.controller;

import com.colanator.forum.dto.PostDTO;
import com.colanator.forum.model.Post;
import com.colanator.forum.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
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

	@RequestMapping(value = "/board", method = GET)
	@ResponseBody
	public ResponseEntity<List<Post>> getPostsOnBoard(@RequestParam Long id, @RequestParam int amount) {
		List<Post> posts;
		if(amount > 0){
			posts = contentService.listNewestPostsOnBoard(id, amount);
		} else {
			posts = contentService.listAllPostsOnBoard(id);
		}
		if(posts != null && !posts.isEmpty()){
			return ResponseEntity.ok(posts);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@RequestMapping(value = "/post", method = GET)
	@ResponseBody
	public ResponseEntity<Post> getPost(@RequestParam Long id) {
		Post post;
		Optional<Post> optionalPost = contentService.getPost(id);
		if(optionalPost.isPresent()){
			post = optionalPost.get();
			return ResponseEntity.ok(post);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@RequestMapping(value = "/post", method = POST)
	public ResponseEntity<String> createPost(@RequestBody PostDTO postDto) {
		final String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
		ResponseEntity<String> result;
		Long boardId = postDto.getBoardId();
		String title = postDto.getTitle();
		String body = postDto.getBody();
		String author = postDto.getAuthor();

		Long success = contentService.addPostToBoard(boardId, title, body, author);
		if (success != null) {
			URI uri = null;
			try {
				uri = new URI(baseUrl + "/post?id=" + success);
			} catch (URISyntaxException e) {
				System.out.println(e.getMessage());
			}
			assert uri != null;
			result = ResponseEntity.created(uri).build();
		} else {
			result = ResponseEntity.notFound().build();
		}
		return result;
	}
}
