package com.yang.javaspringsoot.modules.account.service.impl;

import com.yang.javaspringsoot.modules.account.dao.ResourceDao;
import com.yang.javaspringsoot.modules.account.entity.Resource;
import com.yang.javaspringsoot.modules.account.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author:
 * @create: 2020-08-25 18:50
 **/
@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceDao resourceDao;

    @Override
    public List<Resource> getResourcesByRoleId(int roleId) {
        return resourceDao.getResourcesByRoleId(roleId);
    }
}
