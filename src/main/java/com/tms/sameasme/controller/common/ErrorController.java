package com.tms.sameasme.controller.common;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {


    @RequestMapping("/error")
    public String errorHandler(HttpServletResponse response, Model model){
        if(response.getStatus()==HttpServletResponse.SC_NOT_FOUND)
        {
            model.addAttribute("error","404");
            model.addAttribute("error_text","We couldn't found this page");
        }else if(response.getStatus()==HttpServletResponse.SC_INTERNAL_SERVER_ERROR){
            model.addAttribute("error","500");
            model.addAttribute("error_text","INTERNAL SERVER ERROR");
        }
        return "error";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
