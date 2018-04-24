package cn.gzsxt.shirospringboot.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import cn.gzsxt.shirospringboot.filter.UserFormAuthenticationFilter;
import cn.gzsxt.shirospringboot.shiro.MyRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {


    /**
     * 创建ShiroFilter 工厂对象
     * @param defaultWebSecurityManager
     * @param userFormAuthenticationFilter
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager,UserFormAuthenticationFilter userFormAuthenticationFilter) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //注入SecurityManager对象
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        Map<String, Filter> filterMap = new LinkedHashMap<String, Filter>();
       // map 访问路径权限
        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("/user/login", "anon");
        map.put("/**", "authc");
        //如果关闭了浏览器,获取不到session里面的东西,我们要写一个Filter来获取subject里面的对应存入到session内
        map.put("/user/index", "userFilter");
        //保存一个获取设置Session信息的自定义过滤器
        filterMap.put("userFilter", userFormAuthenticationFilter);
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        //没有权登录直接跳到登录页面
        shiroFilterFactoryBean.setLoginUrl("/user/toLogin");

        shiroFilterFactoryBean.setFilters(filterMap);
        return shiroFilterFactoryBean;
    }

    /**
     * SecurityManager 关联Realm
     * @param myRealm
     * @param cookieRememberMeManager
     * @return
     */
    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(MyRealm myRealm, CookieRememberMeManager cookieRememberMeManager) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(myRealm);
        //
        defaultWebSecurityManager.setRememberMeManager(cookieRememberMeManager);
        return defaultWebSecurityManager;
    }


    @Bean
    public MyRealm myRealm() {
        MyRealm realm = new MyRealm();
        return realm;
    }

    /**
     * thymeleaf 整合 shiro标签库
     * thymeleaf页面想要用shiro的标签,必须得注册这个bean
     * @return
     */
    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }

    /**
     * 创建CookieRemenbernMe.
     * @param simpleCookie
     * @return
     */
    @Bean
    public CookieRememberMeManager cookieRememberMeManager(SimpleCookie simpleCookie) {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(simpleCookie);
        return cookieRememberMeManager;
    }

    /**
     * 给Cookie起名字和存活的时间
     * @return
     */
    @Bean
    public SimpleCookie simpleCookie() {
        //创建cookie对象,给cookie起名称
        SimpleCookie cookie = new SimpleCookie("rememberMe");
        //存活时间
        cookie.setMaxAge(120);
        return cookie;
    }

    /**
     * 自定义的设置Session里面的User 的Filter
     * @return
     */
    @Bean
    public UserFormAuthenticationFilter userFormAuthenticationFilter() {
        UserFormAuthenticationFilter filter = new UserFormAuthenticationFilter();
        return filter;
    }


}
