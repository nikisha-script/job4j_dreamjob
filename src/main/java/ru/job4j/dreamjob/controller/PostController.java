package ru.job4j.dreamjob.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.dreamjob.model.Post;
import ru.job4j.dreamjob.service.CityService;
import ru.job4j.dreamjob.service.PostService;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;


@Controller
@ThreadSafe
public class PostController {

    private final PostService store;
    private final CityService cityService;

    public PostController(PostService postService, CityService cityService) {
        this.store = postService;
        this.cityService = cityService;
    }

    @GetMapping("/posts")
    public String posts(Model model, HttpSession session) {
        MySession.getSession(model, session);
        model.addAttribute("posts", store.findAll());
        return "posts";
    }

    @GetMapping("/formAddPost")
    public String addPost(Model model, HttpSession session) {
        MySession.getSession(model, session);
        model.addAttribute("cities", cityService.getAllCities());
        return "addPost";
    }

    @PostMapping("/createPost")
    public String createPost(@ModelAttribute Post post,
                             @RequestParam(value = "visible", defaultValue = "false") boolean visible,
                             @RequestParam("city.id") int idCity) {
        post.setCreated(LocalDateTime.now());
        post.setVisible(visible);
        post.setIdCity(idCity);
        store.add(post);
        return "redirect:/posts";
    }

    @GetMapping("/formUpdatePost/{postId}")
    public String formUpdatePost(Model model, @PathVariable("postId") int id, HttpSession session) {
        MySession.getSession(model, session);
        model.addAttribute("post", store.findById(id));
        model.addAttribute("cities", cityService.getAllCities());
        return "updatePost";
    }

    @PostMapping("/updatePost")
    public String updatePost(@ModelAttribute Post post,
                             @RequestParam(value = "visible", defaultValue = "false") boolean visible,
                             @RequestParam("city.id") int idCity) {
        post.setVisible(visible);
        post.setIdCity(idCity);
        store.update(post);
        return "redirect:/posts";
    }

    @GetMapping("/deletePost/{postId}")
    public String deletePost(@PathVariable("postId") Integer id) {
        store.delete(id);
        return "redirect:/posts";
    }



}
