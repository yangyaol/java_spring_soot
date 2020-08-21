package com.yang.javaspringsoot.modules.account.service;

import com.github.pagehelper.PageInfo;
import com.yang.javaspringsoot.modules.account.entity.User;
import com.yang.javaspringsoot.modules.common.vo.Result;
import com.yang.javaspringsoot.modules.common.vo.SearchVo;

public interface UserService {

    Result<User> insertUser(User user);

    Result<User> login(User user);

    PageInfo<User> getUsersBySearchVo(SearchVo searchVo);
}
