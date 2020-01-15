package com.contract.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.contract.annotation.AllowAnonymous;
import com.contract.consts.WebConsts;

@Controller
@RequestMapping(value = "/user")
public class UserController extends AbstractController {

    @GetMapping({"/list", "/"})
    public String index(Model model) {
        return "user/list";
    }

    @GetMapping("/logout")
    @AllowAnonymous
    public String logout(Model model, HttpServletRequest request) {

        request.getSession(false).removeAttribute(WebConsts.USER_ID);

        return "redirect:/login";
    }
}
