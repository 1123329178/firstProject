package com.javaproject.demo.controller;

import com.javaproject.demo.mapper.UserMapper;
import com.javaproject.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.CollationKey;


@Controller
public class LoginsController {

    @Autowired
    UserMapper userMapper;

    @RequestMapping(value = "/Logins",method = {RequestMethod.POST,RequestMethod.GET})
    public String logins(@RequestParam(value = "accountid", required = false) String accountid,
                         @RequestParam(value = "password", required = false) String password,
                         HttpServletRequest request,
                         Model model) {
        model.addAttribute("accountid", accountid);
        model.addAttribute("password", password);
        if (accountid == null || accountid == "") {
            model.addAttribute("error", "账号不能为空");
            return "Logins";
        }
        if (password == null || password == "") {
            model.addAttribute("error", "密码不能为空");
            return "Logins";
        }
        User user = new User();
        user=userMapper.findByAccount_Id(accountid,password);
       if (user !=null) {
           model.addAttribute("error", "登陆成功");
       }else
       {
           model.addAttribute("error","登录失败");
           return "Logins";
       }
        return "publish";
    }
    @GetMapping("/logout")
        public String logout(HttpServletRequest request,
                             HttpServletResponse response){
        request.getSession().removeAttribute("user");
            Cookie cookie = new Cookie("token","null");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
            return "redirect:/";
        }
}