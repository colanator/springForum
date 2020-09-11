package com.colanator.forum.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReplyRepository extends CrudRepository<Post, Long> {

	Optional<Post> findById(Long id);

}
