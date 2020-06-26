package com.tms.sameasme.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController extends BaseController {

    @GetMapping("/")
    public String getMain(){
        return "index";
    }

    @GetMapping("/registration")
    public String getRegistration(){
        return "registration";
    }
}
