package com.sc.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Auther: An
 * @Date: Created in 11:012019/8/24
 * @Description:
 */
@Controller
public class indexController{

    @GetMapping("/")
    public String index(){
        return "index";
    }
}
