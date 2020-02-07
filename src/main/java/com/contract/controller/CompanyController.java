package com.contract.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.contract.form.CompanyForm;
import com.contract.service.CompanyService;

@Controller
@RequestMapping(value = "/company")
public class CompanyController extends AbstractController {

    @Autowired
    private CompanyService companyService;

    @GetMapping({"/", "/list"})
    public String index(Model model, CompanyForm companyForm) {
        model.addAttribute("form", companyForm);
        model.addAttribute("companyList", companyService.findAllWithPagination(companyForm));
        return "company/list";
    }

    @GetMapping("/add")
    public String add(Model model) {
        return "company/form";
    }

    @GetMapping("/edit/{id}")
    public String add(Model model, @PathVariable(name = "id") String id) {
        System.out.println(id);
        return "company/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable(name = "id") String id) {
        System.out.println(id);
        return "company/form";
    }
}
