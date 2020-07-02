package com.tms.sameasme.controller;

import com.tms.sameasme.model.user.User;
import com.tms.sameasme.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserRestController extends BaseController {
    private final UserRepository userRepository;

    @Autowired
    public UserRestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(value = "/getAllUsers")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
}
