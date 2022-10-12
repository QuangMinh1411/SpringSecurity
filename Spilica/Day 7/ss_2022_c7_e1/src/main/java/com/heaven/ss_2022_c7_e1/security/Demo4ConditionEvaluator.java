package com.heaven.ss_2022_c7_e1.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class Demo4ConditionEvaluator {
    public boolean condition(){
        var a = SecurityContextHolder.getContext().getAuthentication();
        if(a.getName().equals("john"))
            return true;
        else
            return false;
    }
}
