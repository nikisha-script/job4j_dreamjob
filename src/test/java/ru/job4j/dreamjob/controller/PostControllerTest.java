package ru.job4j.dreamjob.controller;

import org.junit.Test;
import org.springframework.ui.Model;
import ru.job4j.dreamjob.model.Post;
import ru.job4j.dreamjob.model.User;
import ru.job4j.dreamjob.service.CityService;
import ru.job4j.dreamjob.service.PostService;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PostControllerTest {

    @Test
    public void whenPosts() {
        List<Post> posts = Arrays.asList(
                new Post(1, "Test", "Test", LocalDateTime.now(), true, 1),
                new Post(2, "Test2", "Test2", LocalDateTime.now(), true, 2)
        );
        Model model = mock(Model.class);
        HttpSession session = mock(HttpSession.class);
        PostService postService = mock(PostService.class);
        when(postService.findAll()).thenReturn(posts);
        CityService cityService = mock(CityService.class);
        PostController postController = new PostController(
                postService,
                cityService
        );
        String page = postController.posts(model, session);
        verify(model).addAttribute("posts", posts);
        assertThat(page, is("posts"));
    }

    @Test
    public void whenCreatePost() {
        Post input = new Post(1, "Test", "Test", LocalDateTime.now(), true, 1);
        PostService postService = mock(PostService.class);
        CityService cityService = mock(CityService.class);
        PostController postController = new PostController(
                postService,
                cityService
        );
        String page = postController.createPost(input, true, 1);
        verify(postService).add(input);
        assertThat(page, is("redirect:/posts"));
    }

    @Test
    public void whenUpdatePost() {
        Post input = new Post(1, "Test", "Test", LocalDateTime.now(), true, 1);
        PostService postService = mock(PostService.class);
        CityService cityService = mock(CityService.class);
        PostController postController = new PostController(
                postService,
                cityService
        );
        String page = postController.updatePost(input, true, 1);
        assertThat(page, is("redirect:/posts"));
    }

    @Test
    public void whenFormUpdatePost() {
        Post input = new Post(1, "Test", "Test", LocalDateTime.now(), true, 1);
        User user = new User(1, "test@mail.ru", "test123");
        PostService postService = mock(PostService.class);
        CityService cityService = mock(CityService.class);
        HttpSession session = mock(HttpSession.class);
        Model model = mock(Model.class);
        PostController postController = new PostController(
                postService,
                cityService
        );
        when(postService.findById(1)).thenReturn(input);
        when(session.getAttribute("user")).thenReturn(user);
        String page = postController.formUpdatePost(model, 1, session);
        verify(model).addAttribute("post", input);
        verify(model).addAttribute("user", user);
        assertThat(page, is("updatePost"));
    }

    @Test
    public void whenDeletePost() {
        Post input = new Post(1, "Test", "Test", LocalDateTime.now(), true, 1);
        PostService postService = mock(PostService.class);
        CityService cityService = mock(CityService.class);
        PostController postController = new PostController(
                postService,
                cityService
        );
        String page = postController.deletePost(input.getId());
        assertThat(page, is("redirect:/posts"));
    }


}