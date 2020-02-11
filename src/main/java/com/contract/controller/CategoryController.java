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
import com.contract.form.CategoryForm;
import com.contract.model.Category;
import com.contract.service.CategoryService;

@Controller
@RequestMapping(value = "/category")
public class CategoryController extends AbstractController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping({"/", "/list"})
    public String index(Model model, CategoryForm categoryForm) {
        model.addAttribute("form", categoryForm);
        model.addAttribute("categoryList", categoryService.findAllWithPagination(categoryForm));
        return "category/list";
    }

    @GetMapping("/add")
    public String add(Model model, CategoryForm categoryForm) {
        model.addAttribute("form", categoryForm);

        return "category/form";
    }

    @PostMapping("/doSave")
    public String doSave(Model model,
                         HttpServletRequest request,
                         CategoryForm categoryForm) {
        if (categoryForm.getCategoryId() != null) {
            Category category = categoryService.getCategoryRepository()
                    .findById(categoryForm.getCategoryId()).orElse(null);

            if (category == null) {
                throw new NotFoundDBException("Mã danh mục không tồn tại.");
            }

            BeanUtils.copyProperties(categoryForm, category, "createdDate", "createdUserId");

            category.setUpdatedDate(new Date());
            category.setUpdatedUserId(getUserId(request));

            categoryService.getCategoryRepository().save(category);

            model.addAttribute("messsage", "Chỉnh sửa danh mục thành công. Xin vui lòng đợi để chuyển hướng.");
            model.addAttribute("redirectPath", "/category/list");
        } else {
            Category category = new Category();
            BeanUtils.copyProperties(categoryForm, category);

            category.setCreatedDate(new Date());
            category.setCreatedUserId(getUserId(request));

            categoryService.getCategoryRepository().save(category);

            model.addAttribute("messsage", "Thêm mới danh mục thành công. Xin vui lòng đợi để chuyển hướng.");
            model.addAttribute("redirectPath", "/category/list");
        }

        model.addAttribute("form", categoryForm);

        return "category/form";
    }

    @GetMapping("/edit/{id}")
    public String add(Model model,
                      @PathVariable(name = "id") String id,
                      CategoryForm categoryForm) {

        Category category = categoryService.getCategoryRepository()
                .findById(NumberUtils.toLong(id)).orElse(null);

        if (category == null) {
            throw new NotFoundDBException("Mã danh mục không tồn tại.");
        }

        BeanUtils.copyProperties(category, categoryForm);
        model.addAttribute("form", categoryForm);

        return "category/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable(name = "id") String id) {
        Category category = categoryService.getCategoryRepository()
                .findById(NumberUtils.toLong(id)).orElse(null);

        if (category == null) {
            throw new NotFoundDBException("Mã danh mục không tồn tại.");
        }

        categoryService.getCategoryRepository().deleteById(category.getCategoryId());

        return "redirect:/category/list";
    }
}
