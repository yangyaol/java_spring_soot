package com.yang.javaspringsoot.config.shiro;

import com.yang.javaspringsoot.modules.account.entity.Resource;
import com.yang.javaspringsoot.modules.account.entity.Role;
import com.yang.javaspringsoot.modules.account.entity.User;
import com.yang.javaspringsoot.modules.account.service.ResourceService;
import com.yang.javaspringsoot.modules.account.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author:
 * @create: 2020-08-25 14:01
 **/
@Component
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private ResourceService resourceService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        User user = (User) principals.getPrimaryPrincipal();
        List<Role> roles = user.getRoles();
        if (roles != null && !roles.isEmpty()) {
            roles.stream().forEach(item -> {
                simpleAuthorizationInfo.addRole(item.getRoleName());
                List<Resource> resources =
                        resourceService.getResourcesByRoleId(item.getRoleId());
                if (resources != null && !resources.isEmpty()) {
                    resources.stream().forEach(resource -> {
                        simpleAuthorizationInfo.addStringPermission(resource.getPermission());
                    });
                }
            });
        }

        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        String userName = (String) token.getPrincipal();
        User user = userService.getUserByUserName(userName);
        if (user == null) {
            throw new UnknownAccountException("The account do not exist.");
        }
        return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
    }
}
