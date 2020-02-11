package com.contract.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController extends AbstractController {

    @GetMapping("/")
    public String index(Model model, HttpServletRequest request) {
        model.addAttribute("company", getCompany(request));

        return "dashboard/index";
    }
}
