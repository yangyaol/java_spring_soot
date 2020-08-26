package com.yang.javaspringsoot.modules.account.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yang.javaspringsoot.config.ResourceConfigBean;
import com.yang.javaspringsoot.modules.account.dao.UserDao;
import com.yang.javaspringsoot.modules.account.dao.UserRoleDao;
import com.yang.javaspringsoot.modules.account.entity.Role;
import com.yang.javaspringsoot.modules.account.entity.User;
import com.yang.javaspringsoot.modules.account.service.UserService;
import com.yang.javaspringsoot.modules.common.vo.Result;
import com.yang.javaspringsoot.modules.common.vo.SearchVo;
import com.yang.javaspringsoot.utils.MD5Util;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author:
 * @create: 2020-08-20 14:02
 **/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserRoleDao userRoleDao;

    @Autowired
    private ResourceConfigBean resourceConfigBean;

    @Override
    public Result<User> insertUser(User user) {
        User userTemp = userDao.getUserByUserName(user.getUserName());
        if (userTemp != null){
            return new Result<User>(Result.ResultStatus.FAILD.status,"User name is repeat.");
        }
        user.setCreateDate(LocalDateTime.now());
        user.setPassword(MD5Util.getMD5(user.getPassword()));
        userDao.insertUser(user);

        userRoleDao.deleteUserRoleByUserId(user.getUserId());
        List<Role> roles = user.getRoles();
        if (roles != null && !roles.isEmpty()) {
            roles.stream().forEach(item -> {
                userRoleDao.insertUserRole(user.getUserId(), item.getRoleId());
            });
        }
        return new Result<User>(Result.ResultStatus.SUCCESS.status,"Insert User Success", user);
    }

    @Override
    public Result<User> login(User user) {

        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken usernamePasswordToken =
                new UsernamePasswordToken(user.getAccountName(),
                        MD5Util.getMD5(user.getPassword()));
        usernamePasswordToken.setRememberMe(user.getRememberMe());

        try {
            subject.login(usernamePasswordToken);
            subject.checkRoles();
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<User>(Result.ResultStatus.FAILD.status,
                    "UserName or password is error.");
        }

        Session session = subject.getSession();
        session.setAttribute("user", (User)subject.getPrincipal());

        return new Result<User>(Result.ResultStatus.SUCCESS.status,
                "Login success.", user);
    }

    @Override
    public PageInfo<User> getUsersBySearchVo(SearchVo searchVo) {
        searchVo.initSearchVo();
        PageHelper.startPage(searchVo.getCurrentPage(), searchVo.getPageSize());

        return new PageInfo<User>(
                Optional.ofNullable(userDao.getUsersBySearchVo(searchVo))
                        .orElse(Collections.emptyList()));
    }

    @Override
    public Result<User> updateUser(User user) {
        User userTemp = userDao.getUserByUserName(user.getUserName());
        if (userTemp != null && userTemp.getUserId() != user.getUserId()){
            return new Result<User>(Result.ResultStatus.FAILD.status,"User name is repeat.");
        }
        userDao.updateUser(user);
        userRoleDao.deleteUserRoleByUserId(user.getUserId());

        List<Role> roles = user.getRoles();
        if (roles != null && !roles.isEmpty()){
            roles.stream().forEach(item ->{
                userRoleDao.insertUserRole(user.getUserId(), item.getRoleId());
            });
        }
        return new Result<User>(
                Result.ResultStatus.SUCCESS.status,"Update success", user);
    }

    @Override
    @Transactional
    public Result<Object> deleteUser(int userId) {
        userDao.deleteUser(userId);
        userRoleDao.deleteUserRoleByUserId(userId);
        return new Result<>(Result.ResultStatus.SUCCESS.status,"Delete success");
    }

    @Override
    public User getUserByUserId(int userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public Result<String> uploadUserImg(MultipartFile file){
        if (file.isEmpty()){
            return new Result<String>(
                    Result.ResultStatus.FAILD.status,"Please select img.");

        }
        String relativePath = "";
        String destFilePath = "";

        try {
            String osName = System.getProperty("os.name");
            if (osName.toLowerCase().startsWith("win")){
                destFilePath = resourceConfigBean.getLocationPathForWindows() +
                        file.getOriginalFilename();
            }else {
                destFilePath = resourceConfigBean.getLocationPathForLinux() +
                        file.getOriginalFilename();
            }

            relativePath = resourceConfigBean.getRelativePath() +
                    file.getOriginalFilename();
            File destFile = new File(destFilePath);
            file.transferTo(destFile);
        }catch (IOException e){
            e.printStackTrace();
            return new Result<String>(Result.ResultStatus.FAILD.status,
                    "Upload failed.");

        }
        return new Result<String>(Result.ResultStatus.SUCCESS.status,
                "Upload success.", relativePath);
    }

    @Override
    @Transactional
    public Result<User> updateUserProfile(User user) {
        User userTemp = userDao.getUserByUserName(user.getUserName());
        if (userTemp != null && userTemp.getUserId() != user.getUserId()){
            return new Result<User>(Result.ResultStatus.FAILD.status, "User name is repeat.");
        }
        userDao.updateUser(user);
        return new Result<User>(Result.ResultStatus.SUCCESS.status, "Edit success.", user);
    }

    @Override
    public User getUserByUserName(String userName) {
        return userDao.getUserByUserName(userName);
    }
}
