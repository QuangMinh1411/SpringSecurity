package com.heaven.ss_2022_c7_e1.controller;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController

public class DemoController {

    @GetMapping("/demo1")
    @PreAuthorize("hasAuthority('read')") //hasAuthority, hasRole, hasAnyAuthorities, hasANyRoles
    public String demo1(){
        return "Demo 1";
    }
    @GetMapping("/demo2")
    @PreAuthorize("hasAnyAuthority('write')")
    public String demo2(){
        return "Demo 2";
    }

    @GetMapping("/demo3/{smth}")
    @PreAuthorize("#something == authentication.principal.username")
    public String demo3(@PathVariable("smth") String something){
        var a = SecurityContextHolder.getContext().getAuthentication();

        return "Demo 3";
    }

    @GetMapping("/demo4/{sth}")
    @PreAuthorize("@demo4ConditionEvaluator.condition()")
    public String demo4(@PathVariable String sth){
        return "Demo 4";
    }
    @GetMapping("/demo5")
    @PostAuthorize("returnObject != 'Demo 5'") // want to restrict the access to some returned value
    public String demo5(){
        System.out.println(":)"); //never use PostAuthorize with methods that change data
        return "Demo 5";
    }
    @GetMapping("/demo6")
    @PreFilter("filterObject.contains('a')")
    public String demo6(@RequestBody List<String> values){
        System.out.println("Values: " +values);
        return "Demo 6";
    }
    //PostFilter =>return type must be either Collections or Array
    @GetMapping("/demo7")
    @PostFilter("filterObject.contains('a')")
    public List<String> demo7(){
        var list = new ArrayList<String>();
        list.add("abcd");
        list.add("bcd");
        list.add("asdf");
        return list;
    }
}
