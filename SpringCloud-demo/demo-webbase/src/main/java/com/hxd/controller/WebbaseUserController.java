package com.hxd.controller;

import com.hxd.commom.User;
import com.hxd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("webUserController")
@Controller
@ResponseBody
public class WebbaseUserController {

    @Autowired
    private UserService userService;

    @RequestMapping("addUser")
    @ResponseBody
    public String addUser(User user){
        return userService.addUser(user);
    }

}
