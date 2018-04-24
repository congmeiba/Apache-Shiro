package cn.gzsxt.realms;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

public class MyRealm extends AuthorizingRealm {


    /**
     * 这是一个授权方法
     * @param principalCollection
     * @return
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //创建SimpleAuthorizationInfo 授权对象
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //给用户添加有些动态的权限授权码
        //info.addStringPermission("update:add");
        //权限授权码可以使用通配符
        info.addStringPermission("update:*");
        //给用户添加角色授权码
        info.addRole("admin");
        return info;
    }

    /**
     * 这是一个认证方法
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = "admin";
        String password = "admin";
        //获取login() 给的登录信息
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        if(!username.equals(token.getUsername())){
            //直接返回null shiro 会抛出UnknownAccountException 就是账号不存在
            return null;
        }
        //返回SimpleAuthenticationInfo
        //参数1:返回的getPrincipal
        //参数2:login()登录信息的密码和我们动态传入的密码自动比较
        //参数3:realm名称,只有在多个realm多个时才会使用,我们只有单一个realm就直接写""
        return new SimpleAuthenticationInfo(username,password,"");
    }
}
