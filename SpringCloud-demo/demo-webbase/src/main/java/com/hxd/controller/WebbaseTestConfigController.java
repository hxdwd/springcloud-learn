package com.hxd.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RefreshScope
public class WebbaseTestConfigController {

    @Value("${holle}")
    private String s;

    @RequestMapping("get")
    public String getCongifValue(){
        return this.s;
    }

}
