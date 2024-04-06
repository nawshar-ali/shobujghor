package com.shobujghor.app.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/get")
    public String test() {
        return Thread.currentThread().toString();
    }
}
