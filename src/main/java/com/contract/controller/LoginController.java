package com.contract.controller;

import com.contract.annotation.AllowAnonymous;
import com.contract.form.LoginForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class LoginController {

    @GetMapping("/login")
    @AllowAnonymous
    public String home(LoginForm loginForm, Model model) {

        model.addAttribute("form",loginForm);

        return "login/index";
    }
}
