package com.aminaventon.blog.service;

import com.aminaventon.blog.model.User;

public interface UserService {
    User findByEmail(String email);
    User save(User user);
    User findById(Long id);
}
