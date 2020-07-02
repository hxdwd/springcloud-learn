package com.hxd.demopcenter.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommonController {

    @RequestMapping("/add")
    public String add(String name){
        return "Holle "+name+" pcenter-server";
    }

}
