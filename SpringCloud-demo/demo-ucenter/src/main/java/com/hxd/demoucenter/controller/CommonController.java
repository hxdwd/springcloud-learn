package com.hxd.demoucenter.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
public class CommonController {

    private static final Logger LOG = Logger.getLogger(CommonController.class.getName());


    @RequestMapping("/add")
    public String add(String name) {
        System.out.println("-------------");
        return "Holle " + name + " ucenter-server";
    }

    @RequestMapping("/hi")
    public String hi(String name) {
        return String.format("hello %s , from port=%s", name, "9991");
    }

}
