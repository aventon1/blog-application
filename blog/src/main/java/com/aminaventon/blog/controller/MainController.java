package com.aminaventon.blog.controller;

import com.aminaventon.blog.model.Post;
import com.aminaventon.blog.model.User;
import com.aminaventon.blog.service.PostService;
import com.aminaventon.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import java.util.List;

/**
 *  MainController for handling incoming HTTP requests and returning an appropriate responses
 *  for pages: home, login, logout
 */
@Controller
public class MainController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    /**
     * This method receives HTTP GET request for /
     * @param model
     * @return index
     */
    @GetMapping("/")
    public String index(Model model) {

        // Get list of all posts, display 3 on home page
        List<Post> posts = postService.getAllPosts();
        List<Post> recentThreePosts = posts.stream()
                .limit(3).collect(Collectors.toList());
        model.addAttribute("recentThreePosts", recentThreePosts);

        return "index";
    }

    /**
     * This method receives HTTP GET request for /login
     * @param model
     * @return login
     */
    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    /**
     * This method receives HTTP GET request for /logout
     * @return "Logged Out!"
     */
    @ResponseBody
    @GetMapping("/logout")
    public String logoutResponse() {
        return "Logged Out!";
    }

}
