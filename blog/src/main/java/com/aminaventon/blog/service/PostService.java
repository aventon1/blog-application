package com.aminaventon.blog.service;

import com.aminaventon.blog.model.Post;
import java.util.List;

public interface PostService {
    List<Post> getAllPosts();
    Post getPostbyUserId(Long id);
    Post getPostById(Long id);
    Post savePost(Post post);
    Post editPost(Post post);
    void deletePostById(Long id);
}
