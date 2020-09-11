package com.colanator.forum.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Date;

@Entity
public class Reply {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;
	private String body;
	private String author;
	private Timestamp creationDate;

	protected Reply() {}

	public Reply(String author, String body) {
		this.author = author;
		this.body = body;

		Date date= new Date();
		long time = date.getTime();
		this.creationDate = new Timestamp(time);
	}

	@Override
	public String toString() {
		return String.format(
				"Reply[id=%d, author='%s', body='%s', creationDate='%s']",
				id, author, body, creationDate);
	}
}
