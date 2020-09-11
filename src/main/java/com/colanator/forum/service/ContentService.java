package com.colanator.forum.service;

import com.colanator.forum.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Struct;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Component
public class ContentService {

	private final PostRepository postRepository;
	private final BoardRepository boardRepository;

	@Autowired
	public ContentService(PostRepository postRepository, BoardRepository boardRepository){
		this.postRepository = postRepository;
		this.boardRepository = boardRepository;
	}

	public List<Post> listAllPostsOnBoard (Long boardId){
		Optional<Board> board = boardRepository.findById(boardId);

		return board.map(Board::getPosts).orElse(null);
	}

	public List<Post> listNewestPostsOnBoard (Long boardId, int numOfNewestPosts){
		Optional<Board> board = boardRepository.findById(boardId);

		List<Post> newestPosts = new ArrayList<>();

		board.ifPresent(value -> value.getPosts().sort((o1, o2) -> {
			if (o1.getCreationDate().after(o2.getCreationDate()))
				return 1;
			if (o2.getCreationDate().after(o1.getCreationDate()))
				return -1;
			return 0;
		}));

		return null;
	}

	public boolean addPostToBoard (Long boardId, String title, String body, String author){
		Optional<Board> board = boardRepository.findById(boardId);

		if (board.isPresent()) {
			try {
				board.get().getPosts().add(new Post(author, title, body));
				boardRepository.save(board.get());
				return true;
			} catch (Exception e){
				return false;
			}
		}
		return false;
	}

	public Optional<Post> getPost (Long postId){
		return postRepository.findById(postId);
	}

	public void addReplyToPost (Long postId, String body, String author){
		Optional<Post> post = postRepository.findById(postId);

		if (post.isPresent()) {
			post.get().getReplies().add(new Reply(author, body));
			postRepository.save(post.get());
		}
	}
}
