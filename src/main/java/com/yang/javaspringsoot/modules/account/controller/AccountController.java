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
     * 127.0.0.1/account/login  -----get
     * @return
     */
    @GetMapping("/login")
    public String loginPage(){

        return "indexSimple";
    }


    /**
     * 127.0.0.1/account/register -----get
     * @return
     */
    @GetMapping("/register")
    public String registerPage(){
        return "indexSimple";
    }

    /**
     * 127.0.0.1/account/users     ==get
     * @return
     */
    @GetMapping("/users")
    public String userPage(){
        return "index";
    }


    /**
     * 127.0.0.1/account/profile ---- get
     */
    @GetMapping("/profile")
    public String profilePage(){
        return "index";
    }
}
