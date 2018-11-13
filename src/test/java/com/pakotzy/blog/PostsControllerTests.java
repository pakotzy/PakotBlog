package com.pakotzy.blog;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pakotzy.blog.exceptions.ResourceNotFoundException;
import com.pakotzy.blog.models.Post;
import com.pakotzy.blog.models.User;
import com.pakotzy.blog.services.PostService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.notNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest
public class PostsControllerTests {
	@Autowired
	private MockMvc mvc;
	@Autowired
	private ObjectMapper mapper;

	@MockBean
	private PostService postService;

	private User user;
	private List<Post> posts;

	@Before
	public void setup() {
		user = new User("pakotzy", "password");
		Post firstPost = new Post(1L, "First post", "First post body", user, LocalDateTime.now());
		Post secondPost = new Post(2L, "Second post", "Second post body", user, LocalDateTime.now());
		Post thirdPost = new Post(3L, "Third post", "Third post body", user, LocalDateTime.now());
		posts = Arrays.asList(firstPost, secondPost, thirdPost);

		Mockito.when(postService.findAll()).thenReturn(posts);

		Mockito.when(postService.findById(2L)).thenReturn(secondPost);
		Mockito.when(postService.findById(4L)).thenThrow(new ResourceNotFoundException("Post not found"));

		Mockito.when(postService.create(notNull())).thenReturn(posts.get(posts.size()-1).getId());
	}


//	GET mapping tests
	@Test
	public void whenGetPosts_returnJsonArray() throws Exception {
		mvc.perform(get("/api/posts")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(3)))
				.andExpect(jsonPath("$[0].author.username", is(user.getUsername())));
	}

	@Test
	public void whenGetPosts_withValidPostId_returnJsonObject() throws Exception {
		mvc.perform(get("/api/posts/2")
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("body", is(posts.get(1).getBody())));
	}

	@Test
	public void whenGetPosts_withOutOfBoundsPostId_return204() throws Exception {
		mvc.perform(get("/api/posts/4"))
				.andExpect(status().isNotFound());
	}

	//TODO
	@Test
	public void whenGetPosts_withInvalidPostId_return40() throws Exception {

	}

//	POST mapping tests
	@Test
	public void whenPostPosts_return201andLocationHeader() throws Exception {
		mvc.perform(post("/api/posts")
			.contentType(MediaType.APPLICATION_JSON)
			.characterEncoding("UTF-8")
			.content(mapper.writeValueAsString(posts.get(2))))
			.andExpect(status().isCreated())
			.andExpect(header().string("Location", is("/api/posts/" + (posts.get(posts.size()-1).getId()))));
	}
}
