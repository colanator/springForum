package com.colanator.forum.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity
public class Board {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;
	private String name;
	@OneToMany(
			cascade = CascadeType.ALL,
			orphanRemoval = true,
			fetch = FetchType.EAGER
	)
	private List<Post> posts;

	protected Board() {}

	public Board(String name) {
		this.name = name;

		this.posts = new ArrayList<>();
	}

	public List<Post> getPosts() {
		return this.posts;
	}

	@Override
	public String toString() {
		return String.format(
				"Board[id=%d, name='%s', posts='%s']",
				id, name, posts);
	}

	public Long getId(){
		return this.id;
	}
}
