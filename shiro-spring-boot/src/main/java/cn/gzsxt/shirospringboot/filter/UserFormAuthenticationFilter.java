package cn.gzsxt.shirospringboot.filter;

import cn.gzsxt.shirospringboot.entity.User;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class UserFormAuthenticationFilter extends FormAuthenticationFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        Subject subject = getSubject(request, response);

        //isAuthenticated 是否登录过,isRemembered 是否用了记住我这个功能
        if(!subject.isAuthenticated() && subject.isRemembered()){

            Session session = subject.getSession(true);

            if(session.getAttribute("User") == null){
                User user = (User)subject.getPrincipal();
                session.setAttribute("User",user);
            }
        }


        return subject.isAuthenticated() || subject.isRemembered();
    }
}
