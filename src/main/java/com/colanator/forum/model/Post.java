package com.colanator.forum.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;

@Entity
public class Post {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String title;
	private String body;
	private String author;
	private Timestamp creationDate;
	@OneToMany(
			cascade = CascadeType.ALL,
			orphanRemoval = true,
			fetch = FetchType.EAGER
	)
	private List<Reply> replies;

	protected Post() {}

	public Post(String author, String title, String body) {
		this.author = author;
		this.title = title;
		this.body = body;

		Date date= new Date();
		long time = date.getTime();
		this.creationDate = new Timestamp(time);

		this.replies = new ArrayList<>();
	}

	public List<Reply> getReplies() {
		return this.replies;
	}

	@Override
	public String toString() {
		return String.format(
				"Post[id=%d, author='%s', title='%s', body='%s', creationDate='%s', replies='%s']",
				id, author, title, body, creationDate, replies);
	}
}
