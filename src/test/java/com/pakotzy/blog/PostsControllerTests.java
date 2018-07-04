package com.pakotzy.blog;

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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class PostsControllerTests {
	@Autowired
	private MockMvc mvc;

	@MockBean
	private PostService postService;

	private User user;
	private List<Post> posts;

	@Before
	public void setup() {
		user = new User("pakotzy", "password");
		Post firstPost = new Post(1L, "First post", "First post body", user, LocalDateTime.now());
		Post secondPost = new Post(2L, "Second post", "Second post body", user, LocalDateTime.now());
		posts = Arrays.asList(firstPost, secondPost);

		Mockito.when(postService.findAll()).thenReturn(posts);
		Mockito.when(postService.findById(1L)).thenReturn(secondPost);
	}

	@Test
	public void whenGetPosts_returnJsonArray() throws Exception {
		mvc.perform(get("/api/posts")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].author.username", is(user.getUsername())));
	}

	@Test
	public void whenGetPosts_withPostId_returnJsonObject() throws Exception {
		mvc.perform(get("/api/posts/1")
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("body", is(posts.get(1).getBody())));
	}
}
