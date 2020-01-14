package com.contract.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/template")
public class TemplateController extends AbstractController {

    @GetMapping({"/list", "/"})
    public String index(Model model) {
        return "template/list";
    }

    @GetMapping("/add")
    public String add(Model model) {
        return "template/form";
    }

    @GetMapping("/edit/{id}")
    public String add(Model model, @PathVariable(name = "id") String id) {
        System.out.println(id);
        return "template/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable(name = "id") String id) {
        System.out.println(id);
        return "template/form";
    }
}
