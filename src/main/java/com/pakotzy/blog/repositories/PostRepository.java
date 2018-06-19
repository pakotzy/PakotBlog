package com.pakotzy.blog.repositories;

import com.pakotzy.blog.models.Post;
import com.pakotzy.blog.models.User;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PostRepository {
	private List<Post> posts = new ArrayList<>();

	public PostRepository() {
		posts.add(new Post(
				(long) (posts.size() - 1),
				"First Post",
				"Body of the First Post",
				new User("pakotzy", "password"),
				LocalDateTime.now()
		));
		posts.add(new Post(
				(long) (posts.size() - 1),
				"Second Post",
				"Body of the Second Post",
				new User("elon", "3l0nksum"),
				LocalDateTime.now()
		));
	}

	public List<Post> findAll() {
		return posts;
	}
}
