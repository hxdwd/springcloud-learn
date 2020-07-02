package com.hxd.demoucenter.controller;

import com.hxd.commom.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("userController")
@RestController
public class UserController {

    @RequestMapping("addUser")
    public String addUser(@RequestBody User user){
        if (null==user.getPassword()||null==user.getUsername())
            return "error";
        return "success";
    }
}
