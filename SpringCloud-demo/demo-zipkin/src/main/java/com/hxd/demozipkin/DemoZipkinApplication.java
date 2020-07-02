package com.hxd.demozipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import zipkin.server.EnableZipkinServer;

@SpringBootApplication
@EnableZipkinServer
@EnableEurekaClient
public class DemoZipkinApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoZipkinApplication.class, args);
    }

}
