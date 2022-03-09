package com.duan.duantotnghiep.controller;

import com.duan.duantotnghiep.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityController {
    @Autowired
    UserService userService;

    // Truy vấn tới trang đăng nhập
    @RequestMapping("/security/login")
    public String login(Model model){
        model.addAttribute("message","Vui lòng đăng nhập!");
        return "client/login";
    }

    @RequestMapping("/security/login/success")
    public String loginSuccess(Model model) {
        model.addAttribute("message","Đăng nhập thành công");
        return "forward:/";
    }

    @RequestMapping("/security/login/error")
    public String loginError(Model model) {
        model.addAttribute("message","Sai thông tin đăng nhập");
        return "client/login";
    }

    @RequestMapping("/security/unauthoried")
    public String unauthoried(Model model) {
        model.addAttribute("message","Không có quyền truy xuất!");
        return "client/login";
    }

    @RequestMapping("/security/logoff/success")
    public String logoffSuccess(Model model) {
        model.addAttribute("message","Bạn đã đăng xuất");
        return "client/login";
    }
    // OAuth2
    @RequestMapping("/oauth2/login/success")
    public String success(Model model, OAuth2AuthenticationToken oauth2) {
        userService.loginFromOAuth2(oauth2);
        model.addAttribute("message","Đăng nhập thành công");
        return "forward:/";
    }
}
