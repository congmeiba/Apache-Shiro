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

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager,UserFormAuthenticationFilter userFormAuthenticationFilter) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        Map<String, Filter> filterMap = new LinkedHashMap<String, Filter>();
        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("/user/login", "anon");
        map.put("/user/index", "userFilter");
        map.put("/**", "authc");
        filterMap.put("userFilter", userFormAuthenticationFilter);
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        shiroFilterFactoryBean.setLoginUrl("/user/toLogin");
        shiroFilterFactoryBean.setFilters(filterMap);
        return shiroFilterFactoryBean;
    }


    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(MyRealm myRealm, CookieRememberMeManager cookieRememberMeManager) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(myRealm);
        defaultWebSecurityManager.setRememberMeManager(cookieRememberMeManager);
        return defaultWebSecurityManager;
    }


    @Bean
    public MyRealm myRealm() {
        MyRealm realm = new MyRealm();
        return realm;
    }

    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }

    @Bean
    public CookieRememberMeManager cookieRememberMeManager(SimpleCookie simpleCookie) {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(simpleCookie);
        return cookieRememberMeManager;
    }


    @Bean
    public SimpleCookie simpleCookie() {
        //创建cookie对象,给cookie起名称
        SimpleCookie cookie = new SimpleCookie("rememberMe");
        //存活时间
        cookie.setMaxAge(120);
        return cookie;
    }

    @Bean
    public UserFormAuthenticationFilter userFormAuthenticationFilter() {
        UserFormAuthenticationFilter filter = new UserFormAuthenticationFilter();
        return filter;
    }


}
