package com.practice.rest.webservices.service;


import com.practice.rest.webservices.entities.Post;
import com.practice.rest.webservices.exceptions.PostNotFoundException;
import com.practice.rest.webservices.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Post getPost(int id) {
        Optional<Post> post = postRepository.findById(id);
        if (!post.isPresent())
            throw new PostNotFoundException("post id: " + id);
        return post.get();
    }
}
