package com.colanator.forum.dto;

public class PostDTO {

    Long boardId;
    String title;
    String body;
    String author;

    public PostDTO(Long boardId, String title, String body, String author) {
        this.boardId = boardId;
        this.title = title;
        this.body = body;
        this.author = author;
    }

    public Long getBoardId() {
        return boardId;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getAuthor() {
        return author;
    }
}
