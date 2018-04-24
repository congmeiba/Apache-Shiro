package cn.gzsxt.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

public class Demo1 {


    @Test
    public void test1(){
        //创建Factory初始化工厂对象
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        //获取SecurityManager对象
        SecurityManager manager = factory.getInstance();
        //初始化SecutiyUtils工具,让SecutiyUtils去操作获取Subject对象
        SecurityUtils.setSecurityManager(manager);
        //获取Subject用户登录对象
        Subject subject = SecurityUtils.getSubject();
        //用户密码令牌
        UsernamePasswordToken token = new UsernamePasswordToken("admin","admin");
        //realm 登录认证
        try {
            subject.login(token);
            System.out.println("登录成功");
            //principal:认证方法后传回的对象.一般我们可以用存入用户对象后,直接拿回这个完整的用户对象
            String principal = (String)subject.getPrincipal();
            System.out.println(principal);
            //以下是权限授权功能
            //权限授权功能分为两种:
            //1:权限授权码
            //isPermitted:string :是否有这个权限授权码,返回true :就是成功授权:false:用户没有这个权限授权码
            boolean b = subject.isPermitted("update:admin");
            System.out.println(b);
            //2:角色授权码
            //hasRole:判读是否有这个角色授权码
            boolean admin = subject.hasRole("admin");
            System.out.println(admin);


        } catch (UnknownAccountException e) {
            System.out.println("账户名不存在");
        } catch (AuthenticationException e) {
            System.out.println("密码不匹配");
        } catch (Exception e){
            e.printStackTrace();
        }

    }


}
