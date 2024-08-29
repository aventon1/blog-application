package com.aminaventon.blog.controller;

import com.aminaventon.blog.model.Post;
import com.aminaventon.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.stream.Collectors;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private PostService postService;

    @GetMapping("/")
    public String index(Model model) {

        // Get list of all posts, display 3 on home page
        List<Post> posts = postService.getAllPosts();
        List<Post> recentThreePosts = posts.stream()
                .limit(3).collect(Collectors.toList());
        model.addAttribute("recentThreePosts", recentThreePosts);

        return "index";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        return "admin";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @ResponseBody
    @GetMapping("/logout")
    public String logoutResponse() {
        return "Logged Out!";
    }

    @GetMapping("/user")
    public String userIndex() {
        return "user/index";
    }


}
