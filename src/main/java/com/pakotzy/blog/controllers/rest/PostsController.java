package com.pakotzy.blog.controllers.rest;

import com.pakotzy.blog.models.Post;
import com.pakotzy.blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/api")
public class PostsController {

	@Autowired
	PostService postService;

	@GetMapping("/posts")
	public List<Post> getPosts() {
		return postService.findAll();
	}

	@GetMapping("/posts/{postId}")
	public Post getPost(@PathVariable() Long postId) {
		return postService.findById(postId);
	}
}
