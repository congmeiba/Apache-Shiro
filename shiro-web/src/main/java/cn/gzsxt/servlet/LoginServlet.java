package cn.gzsxt.servlet;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rememberMe = request.getParameter("rememberMe");

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        if(rememberMe.isEmpty() && "1".equals(rememberMe)){
            token.setRememberMe(true);
        }
        try {
            //1.认证用户
            subject.login(token);
            String principal = (String) subject.getPrincipal();
            request.getSession().setAttribute("username",principal);
            response.sendRedirect("index.jsp");
            //不用再用户登录这里设置授权,因为在ini配置文件中要访问到才会去realm查看他有没授权
            return;
        } catch (UnknownAccountException e) {
            request.setAttribute("msg","账号不存在");
        } catch (AuthenticationException e){
            request.setAttribute("msg","密码不正确");
        }catch (Exception e){
            request.setAttribute("msg","请于管理员联系");
        }

        request.getRequestDispatcher("login.jsp").forward(request,response);

    }
}
