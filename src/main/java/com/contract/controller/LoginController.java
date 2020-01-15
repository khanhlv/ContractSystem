package com.contract.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.contract.annotation.AllowAnonymous;
import com.contract.consts.WebConsts;
import com.contract.form.LoginForm;


@Controller
public class LoginController {

    @GetMapping("/login")
    @AllowAnonymous
    public String home(LoginForm loginForm, Model model) {

        model.addAttribute("form",loginForm);

        return "login/index";
    }

    @PostMapping("/doLogin")
    @AllowAnonymous
    public String doLogin(LoginForm loginForm, Model model, HttpServletRequest request) {

        model.addAttribute("form",loginForm);
        model.addAttribute("messsage", "Tên đăng nhập hoặc mật khẩu không đúng.");

        request.getSession(true).setAttribute(WebConsts.USER_ID, "ADMIN");

        List<String> permissionList = new ArrayList<>();
        permissionList.add("/");
        permissionList.add("/category/list");
        permissionList.add("/category/add");
        permissionList.add("/category/edit");
        permissionList.add("/category/delete");
        request.getSession(true).setAttribute(WebConsts.USER_PERMISSION, permissionList);

        return "redirect:" + loginForm.getReturnPath();
    }
}
