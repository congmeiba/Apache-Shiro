package cn.gzsxt.shirospringboot.filter;

import cn.gzsxt.shirospringboot.entity.User;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 自定义认证过滤器,加入RemenberMe功能
 */
public class UserFormAuthenticationFilter extends FormAuthenticationFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        //获取一个subject
        Subject subject = getSubject(request, response);

        //isAuthenticated 如果为false 的话是现在没有登陆过,而 isRemembered 为 true 的话是通过记住我进来的
        //证明是通过记住我这个功能进来的
        if(!subject.isAuthenticated() && subject.isRemembered()){
            //获取当前的Session
            Session session = subject.getSession(true);
            //判断这个session里面有没有这个User
            if(session.getAttribute("User") == null){
                //在subject获取tPrincipal(认证时返回的)
                User user = (User)subject.getPrincipal();
                //把这个User 保存到session内
                session.setAttribute("User",user);
            }
        }
        return subject.isAuthenticated() || subject.isRemembered();
    }
}
