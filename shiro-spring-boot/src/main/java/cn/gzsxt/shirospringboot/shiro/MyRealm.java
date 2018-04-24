package cn.gzsxt.shirospringboot.shiro;

import cn.gzsxt.shirospringboot.entity.User;
import cn.gzsxt.shirospringboot.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import java.util.List;

public class MyRealm extends AuthorizingRealm {

    @Resource
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user = (User) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        if(1 == user.getId()){
            List<String> all = userService.findByPermissionAll();
            for (String str:all
                 ) {
                info.addStringPermission(str);
            }
            return info;
        }
        List<String> permission = userService.findByPermission(user.getId());
        for (String str : permission
                ) {
            info.addStringPermission(str);
        }
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        User user = userService.findByUser(token.getUsername());
        if (user == null || !token.getUsername().equals(user.getUsername())) {
            return null;
        }
        System.out.println(getName());
        return new SimpleAuthenticationInfo(user, user.getPassword(), "");
    }
}
