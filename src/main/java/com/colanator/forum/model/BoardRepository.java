package com.colanator.forum.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface BoardRepository extends CrudRepository<Board, Long> {

	Optional<Board> findById(Long id);

}
