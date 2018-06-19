package com.pakotzy.blog.services;

import com.pakotzy.blog.models.Post;

import java.util.List;

public interface PostService {
	List<Post> findAll();
	List<Post> findLatest5();
	Post findById(Long id);
	Post create(Post post);
	Post edit(Post post);
	Post delteById(Long id);
}
