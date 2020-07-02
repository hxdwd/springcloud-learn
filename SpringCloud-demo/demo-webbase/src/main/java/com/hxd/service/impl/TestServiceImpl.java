package com.hxd.service.impl;

import com.hxd.service.TestService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

@Component
public class TestServiceImpl implements TestService {



    @Override
    public String test(@RequestParam(value = "name")String name) {
        return "hello  "+name+"  back";
    }
}
