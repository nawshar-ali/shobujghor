package com.shobujghor.app.contentmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableFeignClients
@ComponentScan(basePackages = {"com.shobujghor.app"})
public class ContentManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ContentManagerApplication.class);
    }
}