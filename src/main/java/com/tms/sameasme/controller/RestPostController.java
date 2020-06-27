package com.tms.sameasme.controller;

import com.tms.sameasme.dto.post.CreatePostDto;
import com.tms.sameasme.model.tag.ETag;
import com.tms.sameasme.model.post.Post;
import com.tms.sameasme.service.post.PostService;
import com.tms.sameasme.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "/post")
public class RestPostController extends BaseController {

    private final PostService postService;
    private final UserService userService;


    @Autowired
    public RestPostController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @PostMapping(value = "/addPost")
    public Post addPost(@RequestBody CreatePostDto dto){
        Post post = dto.updatePost(dto);
        post.setUser(userService.findUserById(getUserId()));
        return postService.savePost(post);
    }

    @GetMapping(value = "/getAllPosts")
   public List<Post> getAllPosts(){
        return postService.findAll();
    }

    @PostMapping(value = "/getAllPostsByTeg")
    public List<Post> getAllByTeg(@RequestParam ETag tag){
        return postService.findAllByTeg(tag);
    }

}
