package com.tms.sameasme.controller.rest;

import com.tms.sameasme.controller.BaseController;
import com.tms.sameasme.mapper.user.UserMapper;
import com.tms.sameasme.model.post.Post;
import com.tms.sameasme.model.user.User;
import com.tms.sameasme.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserRestController extends BaseController {
    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public List<UserMapper> getAllUsers(){
        return UserMapper.getAllUsers(userService.findAll());
    }

}
