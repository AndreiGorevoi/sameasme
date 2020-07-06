package com.tms.sameasme.controller.common;

import com.tms.sameasme.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "admin")
public class AdminController extends BaseController {

    @GetMapping(value = "deletePostForm")
    public String getDeletePostForm(){
        return "deletePostForm";
    }

    @GetMapping(value = "createUserForm")
    public String getDeleteUserForm(){
        return "createUserForm";
    }


}
