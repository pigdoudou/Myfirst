package com.sc.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: An
 * @Date: Created in 11:012019/8/24
 * @Description:
 */
@RestController
public class hello {

    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }
}
