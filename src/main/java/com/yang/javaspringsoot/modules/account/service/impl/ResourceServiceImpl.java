package com.yang.javaspringsoot.modules.account.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yang.javaspringsoot.modules.account.dao.ResourceDao;
import com.yang.javaspringsoot.modules.account.dao.RoleResourceDao;
import com.yang.javaspringsoot.modules.account.entity.Resource;
import com.yang.javaspringsoot.modules.account.entity.Role;
import com.yang.javaspringsoot.modules.account.service.ResourceService;
import com.yang.javaspringsoot.modules.common.vo.Result;
import com.yang.javaspringsoot.modules.common.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author:
 * @create: 2020-08-25 18:50
 **/
@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceDao resourceDao;

    @Autowired
    private RoleResourceDao roleResourceDao;

    @Override
    public List<Resource> getResourcesByRoleId(int roleId) {
        return resourceDao.getResourcesByRoleId(roleId);
    }

    @Override
    public Result<Resource> editResource(Resource resource) {
        Resource resourceTemp = resourceDao.getResourceByPermission(resource.getPermission());
        if (resourceTemp != null && resourceTemp.getResourceId() != resource.getResourceId()){
                return new Result<Resource>(Result.ResultStatus.FAILD.status,"Resource permission is repeat.");
        }
        if (resource.getResourceId() > 0){
            resourceDao.updateResource(resource);
        }else {
            resourceDao.addResource(resource);
        }

        roleResourceDao.deletRoleResourceByResourceId(resource.getResourceId());
        if (resource.getRoles() !=null && !resource.getRoles().isEmpty()){
            for (Role role: resource.getRoles()) {
                roleResourceDao.addRoleResource(role.getRoleId(), resource.getResourceId());
            }
        }
        return new Result<Resource>(Result.ResultStatus.SUCCESS.status,"success",resource);
    }

    @Override
    public Result<Resource> deleteResource(int resourceId) {
        roleResourceDao.deletRoleResourceByResourceId(resourceId);
        resourceDao.deleteResource(resourceId);
        return new Result<Resource>(Result.ResultStatus.SUCCESS.status, "");
    }

    @Override
    public PageInfo<Resource> getResources(SearchVo searchVo) {
        searchVo.initSearchVo();
        PageHelper.startPage(searchVo.getCurrentPage(), searchVo.getPageSize());
        return new PageInfo(
                Optional.ofNullable(resourceDao.getResourcesBySearchVo(searchVo))
                        .orElse(Collections.emptyList())
        );
    }

    @Override
    public Resource getResourceById(int resourceId) {
        return resourceDao.getResourceById(resourceId);
    }
}
