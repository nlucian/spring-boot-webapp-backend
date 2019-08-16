package com.practice.rest.webservices.api;


import com.practice.rest.webservices.entities.Post;
import com.practice.rest.webservices.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/posts")
public class PostsApi {

    @Autowired
    private PostService postService;

    @GetMapping("/{id}")
    public Post getPostbyId(@PathVariable int id) {
        return postService.getPost(id);
    }
}
