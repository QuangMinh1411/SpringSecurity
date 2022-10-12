package com.heaven.ss_2022_c7_e1.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    @GetMapping("/demo1")
    @PreAuthorize("hasAuthority('read')")
    public String demo1(){
        return "Demo 1";
    }
    @GetMapping("/demo2")
    @PreAuthorize("hasAnyAuthority('write')")
    public String demo2(){
        return "Demo 2";
    }
}