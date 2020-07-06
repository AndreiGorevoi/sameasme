package com.tms.sameasme.controller.rest;

import com.tms.sameasme.controller.BaseController;
import com.tms.sameasme.model.role.ERole;
import com.tms.sameasme.model.user.User;
import com.tms.sameasme.repository.user.UserRepository;
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

    @GetMapping(value = "/getAllUsers")
    public List<User> getAllUsers(){
        return userService.findAll();
    }




}
