package com.yang.javaspringsoot.modules.account.service;

import com.yang.javaspringsoot.modules.account.entity.Resource;

import java.util.List;

/**
 * @author:
 * @create: 2020-08-25 18:49
 **/
public interface ResourceService {

    List<Resource> getResourcesByRoleId(int roleId);
}
