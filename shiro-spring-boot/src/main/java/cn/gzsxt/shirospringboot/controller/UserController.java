package cn.gzsxt.shirospringboot.controller;


import cn.gzsxt.shirospringboot.entity.User;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {


    @RequestMapping("/toLogin")
    public String toLogin() {
        return "login";
    }


    @RequestMapping("/login")
    public String login(User user,String rememberMe, Model model, HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        Md5Hash hash = new Md5Hash(user.getUsername(),user.getPassword(),2);
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), hash.toString());
        if(!"".equals(rememberMe) || "1".equals(rememberMe)){
            token.setRememberMe(true);
        }
        try {
            subject.login(token);
            request.getSession().setAttribute("User",user);
            return "index";
        } catch (UnknownAccountException e) {
            model.addAttribute("msg","用户民不存在");
        } catch (AuthenticationException e) {
            model.addAttribute("msg","密码不正确");
        } catch (Exception e){
            model.addAttribute("msg","请于管理员联系");
        }
        return "login";
    }

    @RequestMapping("/logout")
    public String logout(){
        SecurityUtils.getSubject().logout();
        return "login";
    }


    @RequestMapping("/index")
    public String index(){
        return "index";
    }

}
