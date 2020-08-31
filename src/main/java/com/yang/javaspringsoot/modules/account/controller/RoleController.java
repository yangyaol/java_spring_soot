package com.yang.javaspringsoot.modules.account.controller;

import com.github.pagehelper.PageInfo;
import com.yang.javaspringsoot.modules.account.entity.Role;
import com.yang.javaspringsoot.modules.account.service.RoleService;
import com.yang.javaspringsoot.modules.common.vo.Result;
import com.yang.javaspringsoot.modules.common.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


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

    @PostMapping(value = "/roles", consumes = MediaType.APPLICATION_JSON_VALUE)
    public PageInfo<Role> getRoles(@RequestBody SearchVo searchVo){
        return roleService.getRoles(searchVo);
    }

    @PostMapping(value = "/role", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result<Role> insertRole(@RequestBody Role role){
        return roleService.editRole(role);
    }

    @PutMapping(value = "/role", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result<Role> updateRole(Role role){
        return roleService.editRole(role);
    }

    @RequestMapping("/role/{roleId}")
    public Role getRole(@PathVariable int roleId){
        return roleService.getRoleById(roleId);

    }

    @DeleteMapping("/role/{roleId}")
    public Result<Role> deleteRole(int roleId){
        return roleService.deleteRole(roleId);
    }

}
