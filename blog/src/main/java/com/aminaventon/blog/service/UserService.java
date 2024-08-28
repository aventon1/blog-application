package com.aminaventon.blog.service;

import com.aminaventon.blog.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User findById(Long id);
    User create(User user);
    User edit(User user);
    void deleteById(Long id);
}
