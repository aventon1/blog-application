package com.aminaventon.blog.repo;

import com.aminaventon.blog.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * RoleRepository is an interface that extends JpaRepository for basic CRUD operations
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
}
