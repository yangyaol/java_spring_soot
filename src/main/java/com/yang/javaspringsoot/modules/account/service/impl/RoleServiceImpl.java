package com.yang.javaspringsoot.modules.account.service.impl;

import com.yang.javaspringsoot.modules.account.dao.RoleDao;
import com.yang.javaspringsoot.modules.account.entity.Role;
import com.yang.javaspringsoot.modules.account.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author:
 * @create: 2020-08-24 15:20
 **/
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Role> getRoles() {
        return Optional.ofNullable(roleDao.getRoles())
                .orElse(Collections.emptyList());
    }
}
