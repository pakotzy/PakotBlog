package com.pakotzy.blog;

import com.pakotzy.blog.models.Post;
import com.pakotzy.blog.models.User;
import com.pakotzy.blog.services.PostService;
import org.junit.Test;
import org.junit.runner.RunWith;
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
import static org.mockito.BDDMockito.given;
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

	@Test
	public void whenGetPosts_returnJsonArray() throws Exception {
		User user = new User("pakotzy", "password");
		Post post = new Post(1L, "First post", "First post body", user, LocalDateTime.now());
		List<Post> posts = Arrays.asList(post);

		given(postService.findAll()).willReturn(posts);

		mvc.perform(get("/api/posts")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].author.username", is(user.getUsername())));
	}
}
