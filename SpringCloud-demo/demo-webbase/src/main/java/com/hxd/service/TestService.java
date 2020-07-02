package com.hxd.service;

import com.hxd.service.impl.TestServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "demo-ucenter", fallback = TestServiceImpl.class)

public interface TestService {
    @RequestMapping("/ucenterTestController/testMethod")
    String test(@RequestParam("name") String name);
}
