package com.colanator.forum.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {

	List<Post> findByAuthor(String author);
	Optional<Post> findById(Long id);

}
