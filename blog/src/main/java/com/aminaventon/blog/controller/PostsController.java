package com.aminaventon.blog.controller;

import com.aminaventon.blog.model.Post;
import com.aminaventon.blog.model.User;
import com.aminaventon.blog.service.PostService;
import com.aminaventon.blog.service.UserService;
import com.aminaventon.blog.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

        // get post by the id
        Post post = postService.getPostById(id);

        // error page if id does not exist
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

        List<User> users = userService.getAllUsers();

        // create post object to hold post form data
        Post post = new Post();
        post.setUser(users.get(0));

        model.addAttribute("post", post);
        model.addAttribute("users", users);
        return "posts/create_post";
    }

    @PostMapping("/posts")
    public String savePost(@ModelAttribute("post") Post post) {

        // save the post
        postService.savePost(post);

        return "redirect:/posts";
    }

    @GetMapping("/posts/edit/{id}")
    @PreAuthorize("isAuthenticated()")
    public String editPostForm(@PathVariable Long id, Model model) {

        // get the post to be edited
        model.addAttribute("post", postService.getPostById(id));
        return "posts/edit_post";
    }

    @PostMapping("/posts/{id}")
    public String editPost(@PathVariable Long id, @ModelAttribute("post") Post post, Model model) {

        // get post from by id
        Post existingPost = postService.getPostById(id);

        // error page if id does not exist
        if (post == null) {
            return "error_page";
        }

        // set id, title, content to edited post
        existingPost.setId(id);
        existingPost.setTitle(post.getTitle());
        existingPost.setContent(post.getContent());

        // save updated post
        postService.editPost(existingPost);
        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}")
    public String deletePost(@PathVariable Long id) {

        // delete post
        postService.deletePostById(id);

        return "redirect:/posts";
    }

}
