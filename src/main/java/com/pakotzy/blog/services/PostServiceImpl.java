package com.pakotzy.blog.services;

import com.pakotzy.blog.models.Post;
import com.pakotzy.blog.models.User;
import com.pakotzy.blog.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {
	// TODO: Connect to the database
	private List<Post> posts = new ArrayList<>();

	@PostConstruct
	private void configure() {
		User user = new User("pakotzy", "password");

		posts.add(new Post(1L, "First post", "Body of the first post", user, LocalDateTime.now()));
		posts.add(new Post(2L, "Second post", "Body of the second post", user, LocalDateTime.now()));
	}

	@Autowired
	PostRepository repository;

	@Override
	public List<Post> findAll() {
		return posts;
	}

	@Override
	public List<Post> findLatest5() {
		return null;
	}

	@Override
	public Post findById(Long id) {
		return posts.get(id.intValue());
	}

	@Override
	public Long create(Post post) {
		posts.add(post);
		return posts.size()-1L;
	}

	@Override
	public Post edit(Post post) {
		return null;
	}

	@Override
	public Post delteById(Long id) {
		return null;
	}
}
