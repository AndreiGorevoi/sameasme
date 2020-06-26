package com.tms.sameasme.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;

public class BaseController {
    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception ex){
        return ex.getLocalizedMessage();
    }

    public Long getUserId(){
//        TODO
        return null;
    }
}
