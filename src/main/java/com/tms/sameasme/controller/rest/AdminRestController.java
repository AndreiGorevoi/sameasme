package com.tms.sameasme.controller.rest;

import com.tms.sameasme.dto.user.UserChangeDto;
import com.tms.sameasme.model.role.ERole;
import com.tms.sameasme.model.user.User;
import com.tms.sameasme.service.post.PostService;
import com.tms.sameasme.service.role.RoleService;
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

    @Autowired
    public AdminRestController(PostService postService, UserService userService, RoleService roleService) {
        this.postService = postService;
        this.userService = userService;
        this.roleService = roleService;
    }

    @DeleteMapping(value = "post")
    public boolean deletePost(@RequestParam(required = false) Long id){
        return postService.deletePostById(id);
    }

    @DeleteMapping(value = "user")
    public List<User> deleteUser(@RequestParam Long id){
        return userService.deleteById(id);
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
    public List<User> changeInfoToUser(@RequestBody UserChangeDto dto){
        User user = userService.findUserById(dto.getId());
        user.setLogin(dto.getNewLogin());
        user.setName(dto.getNewName());
        user.setRoles(dto.getNewRoles().stream().map(role->roleService.getRoleByName(ERole.valueOf(role)))
                .collect(Collectors.toList()));
        userService.addUser(user);
        return userService.findAll();
    }
}
