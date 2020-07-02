package com.hxd.controller;

import com.hxd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
public class WebbaseZipkinController {
    @Autowired
    private UserService userService;

    private static final Logger LOG = Logger.getLogger(WebbaseZipkinController.class.getName());

    @RequestMapping("hi")
    public String hi(String name){
        return userService.hi(name);
    }


}
