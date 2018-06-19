package com.pakotzy.blog.models;

import java.time.LocalDateTime;

public class Post {
	private Long id;
	private String title;
	private String body;
	private User author;
	private LocalDateTime date = LocalDateTime.now();

	public Post() { }

	public Post(Long id, String title, String body, User author, LocalDateTime date) {
		this.id = id;
		this.title = title;
		this.body = body;
		this.author = author;
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}
}
