package com.contract.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/category")
public class CategoryController extends AbstractController {

    @GetMapping({"/", "/list"})
    public String index(Model model) {
        return "category/list";
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
}
