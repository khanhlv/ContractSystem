package com.contract.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/add")
    public String add(Model model) {
        return "category/form";
    }

    @GetMapping("/edit/{id}")
    public String add(Model model, @PathVariable(name = "id") String id) {
        System.out.println(id);
        return "category/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable(name = "id") String id) {
        System.out.println(id);
        return "category/form";
    }

    @GetMapping("/logout")
    @AllowAnonymous
    public String logout(Model model, HttpServletRequest request) {

        request.getSession(false).removeAttribute(WebConsts.USER_ID);

        return "redirect:/login";
    }
}
