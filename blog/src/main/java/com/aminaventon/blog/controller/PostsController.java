package com.aminaventon.blog.controller;

import com.aminaventon.blog.model.Post;
import com.aminaventon.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostsController {

    @Autowired
    private PostService postService;

    @RequestMapping("/posts/view/{id}")
    public String view(@PathVariable("id") Long id, Model model) {
        Post post = postService.getPostById(id);

        if (post == null) {
            //result.rejectValue("post", null, "Oops! No post found");
            return "redirect:/";
        }

        model.addAttribute("post", post);
        return "posts/view";
    }

    @RequestMapping("/posts")
    public String listPosts(Model model) {
        //System.out.println("IN  PostsController->listPosts()");

        // TODO: get posts by user id
        // all posts for now
        List<Post> posts = postService.getAllPosts();

        model.addAttribute("posts", posts);
        return "posts/posts";
    }

    @GetMapping("/posts/new")
    public String createPostForm(Model model) {

        // create post object to hold post form data
        Post post = new Post();

        model.addAttribute("post", post);
        return "posts/create_post";
    }

    @PostMapping("/posts")
    public String savePost(@ModelAttribute("post") Post post) {
        System.out.println("IN  PostsController->savePost()");
        postService.savePost(post);
        return "redirect:/posts";
    }

}
