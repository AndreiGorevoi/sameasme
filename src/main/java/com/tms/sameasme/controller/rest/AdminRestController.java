package com.tms.sameasme.controller.rest;

import com.tms.sameasme.service.post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "admin")
public class AdminRestController {
    private final PostService postService;

    @Autowired
    public AdminRestController(PostService postService) {
        this.postService = postService;
    }

    @DeleteMapping(value = "deletePost")
    public boolean deletePost(@RequestParam(required = false) Long id){
        return postService.deletePostById(id);
    }
}
