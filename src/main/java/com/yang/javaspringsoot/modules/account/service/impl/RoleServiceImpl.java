package com.yang.javaspringsoot.modules.account.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yang.javaspringsoot.modules.account.dao.RoleDao;
import com.yang.javaspringsoot.modules.account.entity.Role;
import com.yang.javaspringsoot.modules.account.service.RoleService;
import com.yang.javaspringsoot.modules.common.vo.Result;
import com.yang.javaspringsoot.modules.common.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public Result<Role> editRole(Role role) {
        Role roleTemp = roleDao.getRoleByRoleName(role.getRoleName());
        if (roleTemp != null && roleTemp.getRoleId() != role.getRoleId()){
            return new Result<Role>(Result.ResultStatus.FAILD.status, "Role name is repeat.");
        }

        if (role.getRoleId() > 0){
            roleDao.updateRole(role);
        }else {
            roleDao.addRole(role);
        }
        return new Result<Role>(Result.ResultStatus.SUCCESS.status, "success", role);
    }

    @Override
    @Transactional
    public Result<Role> deleteRole(int roleId) {
        roleDao.deleteRole(roleId);
        return new Result<Role>(Result.ResultStatus.SUCCESS.status,"");
    }

    @Override
    public PageInfo<Role> getRoles(SearchVo searchVo) {
        searchVo.initSearchVo();
        PageHelper.startPage(searchVo.getCurrentPage(), searchVo.getPageSize());
        return new PageInfo<>(
                Optional.ofNullable(roleDao.getRolesBySearchVo(searchVo))
                        .orElse(Collections.emptyList())
        );
    }

    @Override
    public List<Role> getRoleByUserId(int userId) {
        return roleDao.getRolesByUserId(userId);
    }

    @Override
    public List<Role> getRolesByResourceId(int resourceId) {
        return roleDao.getRolesByResourceId(resourceId);
    }

    @Override
    public Role getRoleById(int roleId) {
        return roleDao.getRoleById(roleId);
    }
}
