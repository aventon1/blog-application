package com.aminaventon.blog.service;

import com.aminaventon.blog.model.Post;
import com.aminaventon.blog.model.Role;
import com.aminaventon.blog.model.User;
import com.aminaventon.blog.repo.UserRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public User save(User user){
        return userRepository.save(user);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).get();
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        List<GrantedAuthority> grantedAuthorities = user
                .getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), grantedAuthorities);
    }
}