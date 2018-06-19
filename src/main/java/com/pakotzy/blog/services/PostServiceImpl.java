package com.pakotzy.blog.services;

import com.pakotzy.blog.models.Post;
import com.pakotzy.blog.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
	@Autowired
	PostRepository repository;

	@Override
	public List<Post> findAll() {
		return repository.findAll();
	}

	@Override
	public List<Post> findLatest5() {
		return null;
	}

	@Override
	public Post findById(Long id) {
		return null;
	}

	@Override
	public Post create(Post post) {
		return null;
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
