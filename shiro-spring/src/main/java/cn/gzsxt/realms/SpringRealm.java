package cn.gzsxt.realms;

import cn.gzsxt.entity.User;
import cn.gzsxt.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import javax.annotation.Resource;

public class SpringRealm extends AuthorizingRealm{

    @Resource
    private UserService userService;



    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermission("add");
        info.addRole("ad");
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        User user = new User();
        user.setUsername(token.getUsername());
        user.setPassword(new String(token.getPassword()));
        user = userService.findByUser(user);

        if(!user.getUsername().equals(token.getUsername())){
            return null;
        }

        return new SimpleAuthenticationInfo(user,user.getPassword(),"");
    }
}
