package com.tms.sameasme.controller.common;

import com.tms.sameasme.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "post")
public class PostController extends BaseController {
    @GetMapping(value = "add")
    public String getAddPostForm(){
        return "addPostForm";
    }

}
