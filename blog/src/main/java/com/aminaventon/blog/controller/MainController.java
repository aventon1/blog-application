package com.aminaventon.blog.controller;

import com.aminaventon.blog.model.Post;
import com.aminaventon.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.stream.Collectors;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private PostService postService;

    @RequestMapping("/")
    public String index(Model model) {

        List<Post> posts = postService.getAllPosts();

        List<Post> recentThreePosts = posts.stream()
                .limit(3).collect(Collectors.toList());
        model.addAttribute("recentThreePosts", recentThreePosts);

        return "index";
    }
}
