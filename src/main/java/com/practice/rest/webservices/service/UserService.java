package com.practice.rest.webservices.service;

import com.practice.rest.webservices.entities.Post;
import com.practice.rest.webservices.entities.User;
import com.practice.rest.webservices.exceptions.UserNotFoundException;
import com.practice.rest.webservices.repository.PostRepository;
import com.practice.rest.webservices.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User getUser(int id) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent())
            throw new UserNotFoundException("id: " + id);
        return user.get();
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(int id) {
        this.userRepository.deleteById(id);
    }

    public Post addPost(int id, Post post) {
        User user = this.getUser(id);
        post.setUser(user);
        return postRepository.save(post);
    }
}
