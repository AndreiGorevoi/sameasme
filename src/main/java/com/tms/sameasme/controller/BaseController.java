package com.tms.sameasme.controller;

import com.tms.sameasme.model.user.AppUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class BaseController {
    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception ex){
        return ex.getLocalizedMessage();
    }

    public Long getUserId(){
        AppUser appUser = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return appUser.getId();
    }
}
