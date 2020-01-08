package com.contract.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController extends AbstractController {

    @GetMapping("/")
    public String index(Model model) {
        return "dashboard/index";
    }
}
