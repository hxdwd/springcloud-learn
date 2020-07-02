package com.hxd.service;

import com.hxd.commom.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@FeignClient("demo-ucenter")
public interface UserService {

    @RequestMapping("/userController/addUser")
    String addUser(@RequestBody(required = false)User user);

    @RequestMapping("/hi")
    String hi(@RequestParam("name")String name);
}
