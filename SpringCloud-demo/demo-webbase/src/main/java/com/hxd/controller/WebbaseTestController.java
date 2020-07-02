package com.hxd.controller;

import com.hxd.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("webTestController")
public class WebbaseTestController {

    @Autowired
    private TestService testService;
    private int i=0;

    @RequestMapping("test")
    public String test(String name, HttpServletRequest request){
        System.out.println(i++);
//        Map<String,String> map=new HashMap<String, String>();
        //每次请求都需要这些相同的参数 所以封装了工具类
//        LogMaputil.builMap(map,request,"webTestController/test");
        //每次需要记录的参数看需求论。将需要记录的参数put到map中
//        map.put("para1",name);
        //记录日志
        //在返回前或者在进入方法的时候记录日志都可以，
        //一般根据分支返回的方法在方法开始的时候及记录日志
//        LogUtil.printLog(map);
        return testService.test(name);
    }
}
