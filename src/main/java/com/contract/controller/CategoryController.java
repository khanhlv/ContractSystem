package com.contract.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/category")
public class CategoryController extends AbstractController {

    @GetMapping({"/", "/list"})
    public String index(Model model) {
        return "category/list";
    }
}
