package com.hxd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class WebbaseCommonController {

    @RequestMapping("index")
    public String index() {
        System.out.println("-------");
        return "index";
    }

    @RequestMapping("sleep")
    public String mySleep(Long s) throws InterruptedException {
        s=s|0;
        Thread.sleep(s);
        return "666";
    }
}
