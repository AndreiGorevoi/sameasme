package com.tms.sameasme.controller;

import com.tms.sameasme.model.role.ERole;
import com.tms.sameasme.model.role.Role;
import com.tms.sameasme.model.user.User;
import com.tms.sameasme.service.role.RoleService;
import com.tms.sameasme.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@Controller
public class RedirectController extends BaseController {

    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;
    private final UserService userService;

    @Autowired
    public RedirectController(PasswordEncoder passwordEncoder, RoleService roleService, UserService userService) {
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
        this.userService = userService;
    }

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

    @GetMapping(value = "/login")
    public String getLoginPage(Model model,
            @RequestParam(required = false) String error){
        if(error==null){
            return "login";
        }
        model.addAttribute("error","error");
        return "login";
    }

    @GetMapping(value = "/registration")
    public String getRegistration(){
        return "registration";
    }

    @PostMapping(value = "/registration")
    public String postRegistration(HttpServletRequest request,Model model){
        if(userService.findUserByLogin(request.getParameter("username"))==null){
            User user = new User();
            user.setLogin(request.getParameter("username"));
            user.setName(request.getParameter("name"));
            user.setPassword(passwordEncoder.encode(request.getParameter("password")));
            user.setRoles(Arrays.asList(roleService.getRoleByName(ERole.USER)));
            userService.addUser(user);
            return "login";
        }
        model.addAttribute("error","user already exist");
        return "registration";

    }

    @GetMapping(value = "/addPostForm")
    public String getAddPostForm(){
        return "addPostForm";
    }
}
