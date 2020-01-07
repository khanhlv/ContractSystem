package com.contract.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.contract.annotation.AllowAnonymous;
import com.contract.form.HomeForm;


@Controller
public class HomeController {

    @GetMapping({"/", "/login"})
    @AllowAnonymous
    public String home(HomeForm homeForm, Model model) {

        model.addAttribute("form",homeForm);

        return "home/index";
    }
}
