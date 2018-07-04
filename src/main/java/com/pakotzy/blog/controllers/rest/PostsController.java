package com.pakotzy.blog.controllers.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pakotzy.blog.models.Post;
import com.pakotzy.blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController()
@RequestMapping("/api")
public class PostsController {

	@Autowired
	PostService postService;
	@Autowired
	ObjectMapper mapper;

	@GetMapping("/posts")
	public List<Post> getPosts() {
		return postService.findAll();
	}

	@GetMapping("/posts/{postId}")
	public Post getPost(@PathVariable() Long postId) {
		return postService.findById(postId);
	}

	@PostMapping("/posts")
	public ResponseEntity<String> addPost(@RequestBody Post post) {
		URI location = URI.create("/api/posts/" + postService.create(post));
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setLocation(location);

		return new ResponseEntity<>(responseHeaders, HttpStatus.CREATED);
	}
}
