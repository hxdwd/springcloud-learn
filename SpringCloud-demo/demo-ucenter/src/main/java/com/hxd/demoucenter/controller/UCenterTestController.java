package com.hxd.demoucenter.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("ucenterTestController")
public class UCenterTestController {

//    @Value("${testValue}")
    private String testValue;

    private int i = 0;

    @RequestMapping("testMethod")
    public String testMethod(String name) {
        if ("mdzz".equals(name)) {  //测试 如果参数是mdzz则手动抛异常
            System.out.println(i++);
            throw new RuntimeException();
        } else {
            System.out.println(i++);
            System.out.println("UCenter收到数据" + name);
            return "hello " + name + "--" + testValue;
        }
    }
}
