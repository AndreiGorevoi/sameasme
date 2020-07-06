package com.tms.sameasme.controller.rest;

import com.tms.sameasme.model.role.ERole;
import com.tms.sameasme.service.post.PostService;
import com.tms.sameasme.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "admin")
public class AdminRestController {
    private final PostService postService;
    private final UserService userService;

    @Autowired
    public AdminRestController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @DeleteMapping(value = "deletePost")
    public boolean deletePost(@RequestParam(required = false) Long id){
        return postService.deletePostById(id);
    }

    @DeleteMapping(value = "deleteUser")
    public boolean deleteUser(@RequestParam Long id){
        return userService.deleteById(id);
    }

    @PostMapping(value = "/addrole")
    public boolean addRole(@RequestParam Long id, @RequestParam ERole role){
        return userService.addRoleToUser(id,role);
    }

    @PostMapping(value = "/deleterole")
    public boolean deleteRole(@RequestParam Long id,@RequestParam ERole role){
        return userService.deleteRoleToUser(id,role);
    }
}
