package com.colanator.forum.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {

	List<Post> findByAuthor(String author);

}
