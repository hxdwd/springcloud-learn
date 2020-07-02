package com.hxd.democonfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableConfigServer
@EnableFeignClients
public class DemoConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoConfigServerApplication.class, args);
    }

}
