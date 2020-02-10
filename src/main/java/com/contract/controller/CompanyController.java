package com.contract.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.contract.exception.NotFoundDBException;
import com.contract.form.CompanyForm;
import com.contract.model.Company;
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
    public String add(Model model, CompanyForm companyForm) {
        model.addAttribute("form", companyForm);

        return "company/form";
    }

    @PostMapping("/doSave")
    public String doSave(Model model,
                         HttpServletRequest request,
                         CompanyForm companyForm) {
        if (companyForm.getCompanyId() != null) {
            Company company = companyService.getCompanyRepository()
                    .findById(companyForm.getCompanyId()).orElse(null);

            if (company == null) {
                throw new NotFoundDBException("Mã công ty không tồn tại.");
            }

            BeanUtils.copyProperties(companyForm, company, "createdDate", "createdUserId");

            company.setUpdatedDate(new Date());
            company.setUpdatedUserId(getUserId(request));

            companyService.getCompanyRepository().save(company);

            model.addAttribute("messsage", "Chỉnh sửa công ty thành công. Xin vui lòng đợi để chuyển hướng.");
            model.addAttribute("redirectPath", "/company/list");
        } else {
            Company company = new Company();
            BeanUtils.copyProperties(companyForm, company);

            company.setCreatedDate(new Date());
            company.setCreatedUserId(getUserId(request));

            companyService.getCompanyRepository().save(company);

            model.addAttribute("messsage", "Thêm mới công ty thành công. Xin vui lòng đợi để chuyển hướng.");
            model.addAttribute("redirectPath", "/company/list");
        }

        model.addAttribute("form", companyForm);

        return "company/form";
    }

    @GetMapping("/edit/{id}")
    public String add(Model model,
                      @PathVariable(name = "id") String id,
                      CompanyForm companyForm) {

        Company company = companyService.getCompanyRepository()
                .findById(NumberUtils.toLong(id)).orElse(null);

        if (company == null) {
            throw new NotFoundDBException("Mã công ty không tồn tại.");
        }

        BeanUtils.copyProperties(company, companyForm);
        model.addAttribute("form", companyForm);

        return "company/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable(name = "id") String id) {
        Company company = companyService.getCompanyRepository()
                .findById(NumberUtils.toLong(id)).orElse(null);

        if (company == null) {
            throw new NotFoundDBException("Mã công ty không tồn tại.");
        }

        companyService.getCompanyRepository().deleteById(company.getCompanyId());

        return "redirect:/company/list";
    }
}
