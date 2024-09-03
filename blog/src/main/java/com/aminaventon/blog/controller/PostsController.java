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

/**
 *  PostsController for handling incoming HTTP requests and returning an appropriate responses
 *  for post pages and CRUD: post, view, edit, delete
 */
@Controller
public class PostsController {

    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;

    public static final String ERROR = "error_page";
    public static final String REDIRECT = "redirect:/posts";

    /**
     * This method receives HTTP GET request for /posts/view/{id}
     * @param id, model
     * @return posts/view
     */
    @GetMapping("/posts/view/{id}")
    public String view(@PathVariable("id") Long id, Model model) {

        // get post by the id
        Post post = postService.getPostById(id);

        // error page if id does not exist
        if (post == null) {
            return ERROR;
        }

        model.addAttribute("post", post);
        return "posts/view";
    }

    /**
     * This method receives HTTP GET request for /posts/posts
     * @param  model
     * @return posts/posts
     */
    @GetMapping("/posts")
    public String listPosts(Model model) {

        List<Post> posts = postService.getAllPosts();

        model.addAttribute("posts", posts);
        return "posts/posts";
    }

    /**
     * This method receives HTTP GET request for posts/create_post
     * @param model
     * @return posts/create_post
     */
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

    /**
     * This method processes HTTP POST request for /posts
     * @param post
     * @return redirect:/posts
     */
    @PostMapping("/posts")
    public String savePost(@ModelAttribute("post") Post post) {

        // save the post
        postService.savePost(post);

        return REDIRECT;
    }

    /**
     * This method receives HTTP GET request for /posts/edit/{id}
     * @param id, model
     * @return posts/edit_post
     */
    @GetMapping("/posts/edit/{id}")
    @PreAuthorize("isAuthenticated()")
    public String editPostForm(@PathVariable Long id, Model model) {

        // get the post to be edited
        model.addAttribute("post", postService.getPostById(id));
        return "posts/edit_post";
    }

    /**
     * This method processes HTTP POST request for /posts/{id}
     * @param id, post, model
     * @return redirect:/posts
     */
    @PostMapping("/posts/{id}")
    public String editPost(@PathVariable Long id, @ModelAttribute("post") Post post, Model model) {

        // get post from by id
        Post existingPost = postService.getPostById(id);

        // error page if id does not exist
        if (post == null) {
            return ERROR;
        }

        // set id, title, content to edited post
        existingPost.setId(id);
        existingPost.setTitle(post.getTitle());
        existingPost.setContent(post.getContent());

        // save updated post
        postService.editPost(existingPost);
        return REDIRECT;
    }

    /**
     * This method receives HTTP GET request for /posts/{id}
     * @param id
     * @return redirect:/posts
     */
    @GetMapping("/posts/{id}")
    public String deletePost(@PathVariable Long id) {

        // delete post
        postService.deletePostById(id);

        return REDIRECT;
    }

}
