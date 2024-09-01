package com.aminaventon.blog.service;

import com.aminaventon.blog.model.Post;
import com.aminaventon.blog.model.User;

import java.util.List;

public interface UserService {
    User findByEmail(String email);
    User save(User user);
    User findById(Long id);
    List<User> getAllUsers();
}
