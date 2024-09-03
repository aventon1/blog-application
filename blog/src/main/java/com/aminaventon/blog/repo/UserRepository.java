package com.aminaventon.blog.repo;

import com.aminaventon.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * UserRepository is an interface that extends JpaRepository for basic CRUD operations
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * This method finds User by email
     * @param email
     * @return User
     */
    User findByEmail(String email);

    /**
     * This method finds Users by last name
     * @param firstName
     * @return List
     */
    List<User> findByFirstName(String firstName);

    /**
     * This method finds Users by last name
     * @param email
     * @return List
     */
    List<User> findByLastName(String email);

}