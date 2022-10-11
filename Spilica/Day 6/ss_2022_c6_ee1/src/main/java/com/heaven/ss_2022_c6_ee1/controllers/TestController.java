package com.heaven.ss_2022_c6_ee1.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping("/test1")
    public String test1(){
        return "Test1";
    }
    @GetMapping("/test2")
    public String test2(){
        return "test2";
    }
}
