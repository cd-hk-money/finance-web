package com.finance.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello world!";
    }

    @GetMapping("/hello-test")
    public String helloTest() {
        return "hello world!";
    }

    @GetMapping("/hello_test")
    public String helloTest2() {
        return "hello world!";
    }
}
