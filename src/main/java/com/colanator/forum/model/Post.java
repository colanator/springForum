package com.colanator.forum.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.sql.Timestamp;

@Entity
public class Post {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String title;
	private String body;
	private String author;
	private Timestamp creationDate;

	protected Post() {}

	public Post(String author, String title, String body) {
		this.author = author;
		this.title = title;
		this.body = body;

		Date date= new Date();
		long time = date.getTime();
		this.creationDate = new Timestamp(time);
	}

	@Override
	public String toString() {
		return String.format(
				"Post[id=%d, author='%s', title='%s', body='%s', creationDate='%s']",
				id, author, title, body, creationDate);
	}
}
