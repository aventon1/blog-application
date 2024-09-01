package com.aminaventon.blog.repo;

import com.aminaventon.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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
}