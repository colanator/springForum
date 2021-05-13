package com.colanator.forum.service;

import com.colanator.forum.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ContentService {

    private final PostRepository postRepository;
    private final BoardRepository boardRepository;

    @Autowired
    public ContentService(PostRepository postRepository, BoardRepository boardRepository) {
        this.postRepository = postRepository;
        this.boardRepository = boardRepository;
    }

    public List<Post> listAllPostsOnBoard(Long boardId) {
        Optional<Board> board = boardRepository.findById(boardId);

        return board.map(Board::getPosts).orElse(null);
    }

    public List<Post> listNewestPostsOnBoard(Long boardId, int numOfNewestPosts) {
        Optional<Board> board = boardRepository.findById(boardId);

        List<Post> newestPosts = new ArrayList<>();

        board.ifPresent(value -> value.getPosts().sort((o1, o2) -> {
            if (o1.getCreationDate().after(o2.getCreationDate()))
                return 1;
            if (o2.getCreationDate().after(o1.getCreationDate()))
                return -1;
            return 0;
        }));

        for (int i = 0; i < numOfNewestPosts; i++) {
            int finalI = i;
            board.ifPresent(sortedBoard -> newestPosts.add(sortedBoard.getPosts().get(finalI)));
        }

        return newestPosts;
    }

    public Long addPostToBoard(Long boardId, String title, String body, String author) {
        Optional<Board> board = boardRepository.findById(boardId);

        if (board.isPresent()) {
            try {
                Post post = new Post(author, title, body);
                postRepository.save(post);
                board.get().getPosts().add(post);
                boardRepository.save(board.get());
                return post.getId();
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    public Optional<Post> getPost(Long postId) {
        return postRepository.findById(postId);
    }

    public void addReplyToPost(Long postId, String body, String author) {
        Optional<Post> post = postRepository.findById(postId);

        if (post.isPresent()) {
            post.get().getReplies().add(new Reply(author, body));
            postRepository.save(post.get());
        }
    }
}
