package com.practice.rest.webservices.api;

import com.practice.rest.webservices.entities.Post;
import com.practice.rest.webservices.entities.User;
import com.practice.rest.webservices.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/users")
public class UserApi {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getUsers() {
        return this.userService.findAllUsers();
    }

    @GetMapping(path = "/{id}/posts")
    public List<Post> getAllUserPosts(@PathVariable int id) {
        return this.userService.getUser(id).getPosts();
    }

    @GetMapping("/{id}")
    public Resource<User> retrieveUser(@PathVariable int id) {
        User user = this.userService.getUser(id);

        //in HATEOAS we have a resource
        Resource<User> resource = new Resource<>(user);
        ControllerLinkBuilder linkTo = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).getUsers());
        resource.add(linkTo.withRel("all-users"));

        return resource;
    }

    @PostMapping
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> saveUser(@Valid @RequestBody User user) {
        this.userService.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId()).toUri();

        //ResponseEntity is meant to represent the entire HTTP response.
        //You can control anything that goes into it: status code, headers, and body.
        return ResponseEntity.created(location).build();
    }

    @PostMapping(path = "/{id}/post")
    public ResponseEntity<Object> createPost(@PathVariable int id, @RequestBody Post post) {
        Post savedPost = this.userService.addPost(id, post);
        URI location = ServletUriComponentsBuilder
                .fromUriString("http://localhost:8080/post")
                .path("/{id}")
                .buildAndExpand(savedPost.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id) {
        this.userService.deleteUser(id);
    }
}
