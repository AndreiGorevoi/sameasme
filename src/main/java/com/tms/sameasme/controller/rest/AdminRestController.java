package com.tms.sameasme.controller.rest;

import com.tms.sameasme.dto.post.UpdatePostDto;
import com.tms.sameasme.dto.user.UpdateUserDto;
import com.tms.sameasme.mapper.user.UserMapper;
import com.tms.sameasme.model.post.Post;
import com.tms.sameasme.model.role.ERole;
import com.tms.sameasme.model.tag.ETag;
import com.tms.sameasme.model.user.User;
import com.tms.sameasme.service.post.PostService;
import com.tms.sameasme.service.role.RoleService;
import com.tms.sameasme.service.tag.TagService;
import com.tms.sameasme.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "admin")
public class AdminRestController {
    private final PostService postService;
    private final UserService userService;
    private final RoleService roleService;
    private final TagService tagService;

    @Autowired
    public AdminRestController(PostService postService, UserService userService, RoleService roleService, TagService tagService) {
        this.postService = postService;
        this.userService = userService;
        this.roleService = roleService;
        this.tagService = tagService;
    }

    @DeleteMapping(value = "post")
    public boolean deletePost(@RequestParam Long id){
        return postService.deletePostById(id);
    }

    @DeleteMapping(value = "user")
    public List<UserMapper> deleteUser(@RequestParam Long id){
        return UserMapper.getAllUsers(userService.deleteById(id));
    }

    @PostMapping(value = "role")
    public boolean addRole(@RequestParam Long id, @RequestParam ERole role){
        return userService.addRoleToUser(id,role);
    }

    @DeleteMapping(value = "role")
    public boolean deleteRole(@RequestParam Long id,@RequestParam ERole role){
        return userService.deleteRoleToUser(id,role);
    }

    @PostMapping(value = "user")
    public List<UserMapper> changeInfoToUser(@RequestBody UpdateUserDto dto){
        User user = userService.findUserById(dto.getId());
        user.setLogin(dto.getNewLogin());
        user.setName(dto.getNewName());
        user.setRoles(dto.getNewRoles().stream().map(role->roleService.getRoleByName(ERole.valueOf(role)))
                .collect(Collectors.toList()));
        userService.addUser(user);
        return  UserMapper.getAllUsers(userService.findAll());
    }

    @PostMapping(value = "post")
    public Post changeInfoToPost(@RequestBody UpdatePostDto dto){
        Post post = postService.getPostById(dto.getId());
        Post updatedPost = dto.updatePost(post);
        updatedPost.setTag(tagService.getTagByName(dto.getTag()));
        return postService.savePost(post);
    }
}
