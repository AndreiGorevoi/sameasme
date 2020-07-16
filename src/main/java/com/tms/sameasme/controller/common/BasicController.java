package com.tms.sameasme.controller.common;

import com.tms.sameasme.controller.BaseController;
import com.tms.sameasme.model.role.Role;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class BasicController extends BaseController {

    @GetMapping(value = "/")
    public String getMain(){
        return "greeting";
    }

    @GetMapping(value = "/wall")
    public String getWall(Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        session.setAttribute("userName", getUserName());
        List<Role> roles = getUserRoles();
        boolean admin = false;
        boolean moderator = false;
        for (Role role : roles) {
            if(role.getName().toString()=="ADMIN"){
                admin=true;
            }
            if(role.getName().toString().equals("MODERATOR")){
                moderator=true;
            }
        }
        model.addAttribute("Admin", admin);
        model.addAttribute("moderator", moderator);
        return "wall";
    }
}
