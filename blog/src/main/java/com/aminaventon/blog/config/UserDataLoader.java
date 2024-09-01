package com.aminaventon.blog.config;


import com.aminaventon.blog.model.Post;
import com.aminaventon.blog.model.User;
import com.aminaventon.blog.service.PostService;
import com.aminaventon.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDataLoader implements CommandLineRunner {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        List<Post> posts = postService.getAllPosts();

        // If db empty, add data
        if (posts.size() == 0) {

            User user1 = new User();
            user1.setFirstName("User1");
            user1.setLastName("User1");
            user1.setEmail("user1@email.com");
            user1.setPassword("password");

            User user2 = new User();
            user2.setFirstName("User2");
            user2.setLastName("User2");
            user2.setEmail("user2@email.com");
            user2.setPassword("password");

            userService.save(user1);
            userService.save(user2);

            Post new_post1 = new Post();
            new_post1.setTitle("This is a blog post title 1");
            new_post1.setContent("This is a blog post");
            new_post1.setUser(user1);

            Post new_post2 = new Post();
            new_post2.setTitle("This is a blog post title 2");
            new_post2.setContent("This is a blog post");
            new_post2.setUser(user2);

            postService.savePost(new_post1);
            postService.savePost(new_post2);
        }

    }
}
