package com.aminaventon.blog.repo;

import com.aminaventon.blog.model.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}
