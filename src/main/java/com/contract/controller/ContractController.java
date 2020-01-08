package com.contract.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/contract")
public class ContractController extends AbstractController {

    @GetMapping({"/list", "/"})
    public String index(Model model) {
        return "contract/list";
    }
}
