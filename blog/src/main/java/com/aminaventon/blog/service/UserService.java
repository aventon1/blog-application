package com.aminaventon.blog.service;

import com.aminaventon.blog.model.Post;
import com.aminaventon.blog.model.User;

import java.util.List;

/**
 * UserService is an interface with abstract CRUD methods
 */
public interface UserService {
    User findByEmail(String email);
    List<User> findByFirstName(String firstName);
    List<User> findByLastName(String email);
    User save(User user);
    User findById(Long id);
    List<User> getAllUsers();
}
