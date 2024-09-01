package com.aminaventon.blog.config;


import com.aminaventon.blog.model.Post;
import com.aminaventon.blog.model.Role;
import com.aminaventon.blog.model.User;
import com.aminaventon.blog.repo.RoleRepository;
import com.aminaventon.blog.service.PostService;
import com.aminaventon.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class UserDataLoader implements CommandLineRunner {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {

        // get posts from db
        List<Post> posts = postService.getAllPosts();

        // if db empty, add data
        if (posts.isEmpty()) {

            // create roles
            Role user = new Role();
            user.setName("ROLE_USER");
            roleRepository.save(user);

            Role admin = new Role();
            user.setName("ROLE_ADMIN");
            roleRepository.save(admin);

            // create users
            User user1 = new User();
            user1.setFirstName("User1");
            user1.setLastName("User1");
            user1.setEmail("user1@email.com");
            user1.setPassword("password");
            Set<Role> roles1 = new HashSet<>();
            roleRepository.findById("ROLE_USER").ifPresent(roles1::add);
            roleRepository.findById("ROLE_ADMIN").ifPresent(roles1::add);
            user1.setRoles(roles1);

            User user2 = new User();
            user2.setFirstName("User2");
            user2.setLastName("User2");
            user2.setEmail("user2@email.com");
            user2.setPassword("password");
            Set<Role> roles2 = new HashSet<>();
            roleRepository.findById("ROLE_ADMIN").ifPresent(roles2::add);
            user2.setRoles(roles2);

            userService.save(user1);
            userService.save(user2);

            // create posts
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
