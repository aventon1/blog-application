package com.aminaventon.blog.service;

import com.aminaventon.blog.model.User;
import com.aminaventon.blog.repo.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * UserServiceImpl implements the UserService interface
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * This method finds User by email
     * @param email
     * @return User
     */
    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    /**
     * This method finds Users by last name
     * @param firstName
     * @return List
     */
    @Override
    public List<User> findByFirstName(String firstName) {
        return userRepository.findByFirstName(firstName);
    }

    /**
     * This method finds Users by last name
     * @param email
     * @return List
     */
    @Override
    public List<User> findByLastName(String email) {
        return userRepository.findByLastName(email);
    }

    /**
     * This method saves a User
     * @param user
     * @return User
     */
    public User save(User user){
        return userRepository.save(user);
    }

    /**
     * This method finds User by id
     * @param id
     * @return
     */
    @Override
    public User findById(Long id) {
        return userRepository.findById(id).get();
    }

    /**
     * This method returns a list of all Users
     * @return List<User>
     */
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * This method authenticates a user by email and password with Spring Security
     * @param email
     * @return
     * @throws UsernameNotFoundException
     */
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