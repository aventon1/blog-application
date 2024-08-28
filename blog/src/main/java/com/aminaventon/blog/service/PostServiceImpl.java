package com.aminaventon.blog.service;

import com.aminaventon.blog.model.Post;
import com.aminaventon.blog.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import java.awt.print.Pageable;
import java.util.List;

@Service
@Primary
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public List<Post> getAllPosts() {
        return this.postRepository.findAll();
    }

    @Override
    public Post getPostById(Long id) {
        return this.postRepository.findById(id).get();
    }

    @Override
    public Post createPost(Post post) {
        return this.postRepository.save(post);
    }

    @Override
    public Post editPost(Post post) {
        return this.postRepository.save(post);
    }

    @Override
    public void deletePostById(Long id) {
        this.postRepository.deleteById(id);
    }
}
