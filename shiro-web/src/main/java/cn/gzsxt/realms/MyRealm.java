package cn.gzsxt.realms;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

public class MyRealm extends AuthorizingRealm {

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //这里进行授权.
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermission("article:add");
        info.addRole("admin");
        return info;
    }


    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = "admin";
        String password = "admin";
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        if (!username.equals(token.getUsername())) {
            return null;
        }
        return new SimpleAuthenticationInfo(username, password, "");
    }
}
