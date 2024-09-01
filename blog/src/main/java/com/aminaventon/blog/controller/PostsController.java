package com.aminaventon.blog.controller;

import com.aminaventon.blog.model.Post;
import com.aminaventon.blog.model.User;
import com.aminaventon.blog.service.PostService;
import com.aminaventon.blog.service.UserService;
import com.aminaventon.blog.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostsController {

    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;

    @GetMapping("/posts/view/{id}")
    public String view(@PathVariable("id") Long id, Model model) {
        Post post = postService.getPostById(id);

        if (post == null) {
            return "error_page";
        }

        model.addAttribute("post", post);
        return "posts/view";
    }

    @GetMapping("/posts")
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


        // hard coded get user
        //User user = userService.findByEmail("user1@email.com");

        // create post object to hold post form data
        Post post = new Post();

        // add account to post
        //post.setUser(user);

        model.addAttribute("post", post);
        return "posts/create_post";
    }

    @PostMapping("/posts")
    public String savePost(@ModelAttribute("post") Post post) {

        postService.savePost(post);
        return "redirect:/posts";
    }

    @GetMapping("/posts/edit/{id}")
    public String editPostForm(@PathVariable Long id, Model model) {

        model.addAttribute("post", postService.getPostById(id));
        return "posts/edit_post";
    }

    @PostMapping("/posts/{id}")
    public String editPost(@PathVariable Long id, @ModelAttribute("post") Post post, Model model) {

        // get post from db by id
        Post existingPost = postService.getPostById(id);

        if (post == null) {
            return "error_page";
        }

        existingPost.setId(id);
        existingPost.setTitle(post.getTitle());
        existingPost.setContent(post.getContent());

        // save updated post object
        postService.editPost(existingPost);
        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}")
    public String deletePost(@PathVariable Long id) {

        postService.deletePostById(id);
        return "redirect:/posts";
    }

}
