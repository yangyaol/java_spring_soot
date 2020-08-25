package com.yang.javaspringsoot.modules.account.controller;

import com.github.pagehelper.PageInfo;
import com.yang.javaspringsoot.modules.account.entity.User;
import com.yang.javaspringsoot.modules.account.service.UserService;
import com.yang.javaspringsoot.modules.common.vo.Result;
import com.yang.javaspringsoot.modules.common.vo.SearchVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author:
 * @create: 2020-08-20 14:01
 **/
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 127.0.0.1/api/login   ----post
     * @param user
     * @return
     */
    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result<User> login(@RequestBody User user){
        return userService.login(user);
    }

    /**
     * 127.0.0.1/api/user   -----post
     * {"userName":"admin","password":"123456"}
     * @param user
     * @return
     */
    @PostMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result<User> insertUser(@RequestBody User user){
        return userService.insertUser(user);
    }

    @RequestMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
    public PageInfo<User> getUsersBySearchVo(@RequestBody SearchVo searchVo){
        return userService.getUsersBySearchVo(searchVo);
    }

    /**
     * 127.0.0.1/api/user  ------put
     * {"userName":"tian2","userImg":"/aaa.jpg","userId":"4"}
     * @param user
     * @return
     */
    @PutMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result<User> updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

    /**
     * 127.0.0.1/api/user/4  -----delete
     * @param userId
     * @return
     */
    @DeleteMapping(value = "/user/{userId}" )
    @RequiresPermissions(value = "/api/user")
    public Result<Object> deleteUser(@PathVariable int userId){
        return userService.deleteUser(userId);
    }

    /**
     * 127.0.0.1/api/user/1      -----get
     * @param userId
     * @return
     */
    @GetMapping("/user/{userId}")
    public User getUserByUserId(@PathVariable int userId){

        return userService.getUserByUserId(userId);
    }

    @PostMapping(value = "/userImg", consumes = "multipart/form-data")
    public Result<String> uploadUserImg(@RequestParam MultipartFile file){
        return userService.uploadUserImg(file);
    }

    @PutMapping(value = "/profile", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result<User> updateUserProfile(@RequestBody User user){
        return userService.updateUserProfile(user);
    }
}
