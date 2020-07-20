package com.tms.sameasme.controller.common;

import com.tms.sameasme.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "admin")
public class AdminController extends BaseController {

    @GetMapping(value = "post")
    public String getDeletePostForm(){
        return "refactorPostForm";
    }

    @GetMapping(value = "user")
    public String getDeleteUserForm(){
        return "refactorUserForm";
    }


}
