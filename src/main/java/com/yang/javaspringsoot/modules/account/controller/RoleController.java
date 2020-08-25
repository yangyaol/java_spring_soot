package com.yang.javaspringsoot.modules.account.controller;

import com.yang.javaspringsoot.modules.account.entity.Role;
import com.yang.javaspringsoot.modules.account.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author:
 * @create: 2020-08-24 15:25
 **/
@RestController
@RequestMapping("/api")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @RequestMapping("/roles")
    public List<Role> getRoles(){
        return roleService.getRoles();
    }
}
