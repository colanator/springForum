package com.colanator.forum.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Post {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String author;
	private String title;
	private String body;

	protected Post() {}

	public Post(String author, String title, String body) {
		this.author = author;
		this.title = title;
		this.body = body;
	}

	@Override
	public String toString() {
		return String.format(
				"Post[id=%d, author='%s', title='%s', body='%s']",
				id, author, title, body);
	}

}
