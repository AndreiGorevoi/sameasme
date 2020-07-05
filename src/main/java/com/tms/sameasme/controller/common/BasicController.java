package com.tms.sameasme.controller.common;

import com.tms.sameasme.controller.BaseController;
import com.tms.sameasme.model.role.Role;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class BasicController extends BaseController {

    @GetMapping(value = "/")
    public String getMain(){
        return "greeting";
    }

    @GetMapping(value = "/wall")
    public String getWall(Model model){
        List<Role> roles = getUserRoles();
        boolean admin = false;
        for (Role role : roles) {
            if(role.getName().toString()=="ADMIN"){
                admin=true;
            }
        }
        model.addAttribute("Admin", admin);
        return "wall";
    }
}
