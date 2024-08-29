package com.aminaventon.blog.service;

import com.aminaventon.blog.model.User;
import com.aminaventon.blog.repo.UserRegistrationDto;

import java.util.List;

public interface UserService {
    User findByEmail(String email);
    User save(UserRegistrationDto registration);
}
