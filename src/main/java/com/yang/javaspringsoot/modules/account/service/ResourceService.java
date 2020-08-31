package com.yang.javaspringsoot.modules.account.service;

import com.github.pagehelper.PageInfo;
import com.yang.javaspringsoot.modules.account.entity.Resource;
import com.yang.javaspringsoot.modules.common.vo.Result;
import com.yang.javaspringsoot.modules.common.vo.SearchVo;

import java.util.List;

/**
 * @author:
 * @create: 2020-08-25 18:49
 **/
public interface ResourceService {

    List<Resource> getResourcesByRoleId(int roleId);

    Result<Resource> editResource(Resource resource);

    Result<Resource> deleteResource(int resourceId);

    PageInfo<Resource> getResources(SearchVo searchVo);

    Resource getResourceById(int resourceId);
}
