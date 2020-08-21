package com.yang.javaspringsoot.modules.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author:
 * @create: 2020-08-20 19:13
 **/
@Controller
@RequestMapping("/common")
public class CommonController {

    @RequestMapping("/dashboard")
    public String dashboardPage(){
        return "index";
    }
}
