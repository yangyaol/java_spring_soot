package com.yang.javaspringsoot.modules.account.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author:
 * @create: 2020-08-17 18:43
 **/

@Controller
@RequestMapping("/account")
public class AccountController {

    /**
     * 127.0.0.1/account/user     ==get
     * @return
     */
    @GetMapping("/user")
    public String userPage(){
        return "index";
    }
}
