package com.tms.sameasme.controller;

import com.tms.sameasme.dto.post.CreatePostDto;
import com.tms.sameasme.model.tag.ETag;
import com.tms.sameasme.model.post.Post;
import com.tms.sameasme.service.post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RController extends BaseController {

    private final PostService postService;

    @Autowired
    public RController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping(value = "/addPost")
    public Post addPost(@RequestBody CreatePostDto dto){
        return postService.savePost(dto.updatePost(dto));
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
