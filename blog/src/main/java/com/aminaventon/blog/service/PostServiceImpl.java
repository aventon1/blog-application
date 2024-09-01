package com.aminaventon.blog.service;

import com.aminaventon.blog.model.Post;
import com.aminaventon.blog.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import java.awt.print.Pageable;
import java.util.List;

/**
 * PostServiceImpl implements the PostService interface
 */
@Service
@Primary
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    /**
     * This method gets a list of all Posts
     * @return List<Post>
     */
    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    /**
     * This method gets a post by its id
     * @param id
     * @return Post
     */
    @Override
    public Post getPostById(Long id) {
        return postRepository.findById(id).get();
    }

    /**
     * This method saves a post
     * @param post
     * @return Post
     */
    @Override
    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    /**
     * This method updates a post
     * @param post
     * @return Post
     */
    @Override
    public Post editPost(Post post) {
        return postRepository.save(post);
    }

    /**
     * This method deletes a post by id
     * @param id
     */
    @Override
    public void deletePostById(Long id) {
        postRepository.deleteById(id);
    }
}
