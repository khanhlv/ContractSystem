package com.contract.controller;

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
import com.contract.form.TemplateForm;
import com.contract.model.Template;
import com.contract.service.CategoryService;
import com.contract.service.CompanyService;
import com.contract.service.TemplateService;

@Controller
@RequestMapping(value = "/template")
public class TemplateController extends AbstractController {

    @Autowired
    private TemplateService templateService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping({"/", "/list"})
    public String index(Model model, TemplateForm templateForm) {
        model.addAttribute("form", templateForm);
        model.addAttribute("templateList", templateService.findAllWithPagination(templateForm));
        model.addAttribute("companyList", companyService.findByStatus(1L));
        model.addAttribute("categoryList", categoryService.findCategoryByStatus(1L));
        return "template/list";
    }

    @GetMapping("/add")
    public String add(Model model, TemplateForm templateForm) {
        model.addAttribute("form", templateForm);

        return "template/form";
    }

    @PostMapping("/doSave")
    public String doSave(Model model,
                         HttpServletRequest request,
                         TemplateForm templateForm) {
//        if (categoryForm.getCategoryId() != null) {
//            Category category = templateService.get()
//                    .findById(categoryForm.getCategoryId()).orElse(null);
//
//            if (category == null) {
//                throw new NotFoundDBException("Mã danh mục không tồn tại.");
//            }
//
//            BeanUtils.copyProperties(categoryForm, category, "createdDate", "createdUserId");
//
//            category.setUpdatedDate(new Date());
//            category.setUpdatedUserId(getUserId(request));
//
//            categoryService.getCategoryRepository().save(category);
//
//            model.addAttribute("messsage", "Chỉnh sửa danh mục thành công. Xin vui lòng đợi để chuyển hướng.");
//            model.addAttribute("redirectPath", "/category/list");
//        } else {
//            Category category = new Category();
//            BeanUtils.copyProperties(categoryForm, category);
//
//            category.setCreatedDate(new Date());
//            category.setCreatedUserId(getUserId(request));
//
//            categoryService.getCategoryRepository().save(category);
//
//            model.addAttribute("messsage", "Thêm mới danh mục thành công. Xin vui lòng đợi để chuyển hướng.");
//            model.addAttribute("redirectPath", "/category/list");
//        }

        model.addAttribute("form", templateForm);

        return "template/form";
    }

    @GetMapping("/edit/{id}")
    public String add(Model model,
                      @PathVariable(name = "id") String id,
                      TemplateForm templateForm) {

        Template template = templateService.getTemplateRepository()
                .findById(NumberUtils.toLong(id)).orElse(null);

        if (template == null) {
            throw new NotFoundDBException("Mã mẫu hợp đồng không tồn tại.");
        }

        BeanUtils.copyProperties(template, templateForm);
        model.addAttribute("form", templateForm);

        return "template/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable(name = "id") String id) {
        Template template = templateService.getTemplateRepository()
                .findById(NumberUtils.toLong(id)).orElse(null);

        if (template == null) {
            throw new NotFoundDBException("Mã mẫu hợp đồng không tồn tại.");
        }

        templateService.getTemplateRepository().deleteById(template.getTemplateId());

        return "redirect:/template/list";
    }
}
