package cn.gzsxt.controller;

import cn.gzsxt.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/shiro")
public class ShiroController {



    @RequestMapping("/toLogin")
    public String toLogin(){
        System.out.println("?");
        return "login";
    }


    @RequestMapping("/login")
    public String login(User user, Model model, HttpServletRequest request){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),user.getPassword());

        try {
            subject.login(token);
            User user1 = (User) subject.getPrincipal();
            request.getSession().setAttribute("User",user1);
            return "index";
        } catch (UnknownAccountException e) {
            model.addAttribute("msg","账号不存在");
        } catch (AuthenticationException e){
            model.addAttribute("msg","密码有误,请重新输入");
        } catch (Exception e){
            model.addAttribute("msg","请与管理员联系");
        }
        return "login";
    }

    @RequestMapping("/insert")
    @RequiresPermissions("admin")
    public String insert(){
        return "insert";
    }
    @RequestMapping("/delete")
    public String delete(){
        return "delete";
    }
    @RequestMapping("/update")
    public String update(){
        return "update";
    }

}
