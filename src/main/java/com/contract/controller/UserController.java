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

import com.contract.annotation.AllowAnonymous;
import com.contract.exception.NotFoundDBException;
import com.contract.form.UserForm;
import com.contract.model.User;
import com.contract.service.CompanyService;
import com.contract.service.UserGroupService;
import com.contract.service.UserService;

@Controller
@RequestMapping(value = "/user")
public class UserController extends AbstractController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserGroupService userGroupService;

    @Autowired
    private CompanyService companyService;

    private void dataLoader(Model model) {
        model.addAttribute("userGroupList", userGroupService.findByStatus(1L));
        model.addAttribute("companyList", companyService.findByStatus(1L));
    }

    @GetMapping({"/list", "/"})
    public String index(Model model, UserForm userForm) {
        model.addAttribute("form", userForm);
        model.addAttribute("userList", userService.findAllWithPagination(userForm));

        dataLoader(model);

        return "user/list";
    }

    @GetMapping("/add")
    public String add(Model model, UserForm userForm) {
        model.addAttribute("form", userForm);

        dataLoader(model);

        return "user/form";
    }

    @PostMapping("/doSave")
    public String doSave(Model model,
                         HttpServletRequest request,
                         UserForm userForm) {
        if (userForm.getUserId() != null) {
            User user = userService.getUserRepository()
                    .findById(userForm.getUserId()).orElse(null);

            if (user == null) {
                throw new NotFoundDBException("Mã tài khoản không tồn tại.");
            }

            BeanUtils.copyProperties(userForm, user, "createdDate", "createdUserId", "username", "password");

            user.setUpdatedDate(new Date());
            user.setUpdatedUserId(getUserId(request));

            userService.getUserRepository().save(user);

            model.addAttribute("messsage", "Chỉnh sửa tài khoản thành công. Xin vui lòng đợi để chuyển hướng.");
            model.addAttribute("redirectPath", "/user/list");
        } else {
            User user = new User();
            BeanUtils.copyProperties(userForm, user);

            user.setCreatedDate(new Date());
            user.setCreatedUserId(getUserId(request));

            userService.getUserRepository().save(user);

            model.addAttribute("messsage", "Thêm mới tài khoản thành công. Xin vui lòng đợi để chuyển hướng.");
            model.addAttribute("redirectPath", "/user/list");
        }

        model.addAttribute("form", userForm);

        dataLoader(model);

        return "user/form";
    }

    @GetMapping("/edit/{id}")
    public String add(Model model,
                      @PathVariable(name = "id") String id,
                      UserForm userForm) {

        User user = userService.getUserRepository()
                .findById(NumberUtils.toLong(id)).orElse(null);

        if (user == null) {
            throw new NotFoundDBException("Mã tài khoản không tồn tại.");
        }

        BeanUtils.copyProperties(user, userForm);
        model.addAttribute("form", userForm);

        dataLoader(model);

        return "user/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable(name = "id") String id) {
        User user = userService.getUserRepository()
                .findById(NumberUtils.toLong(id)).orElse(null);

        if (user == null) {
            throw new NotFoundDBException("Mã tài khoản không tồn tại.");
        }

        userService.getUserRepository().deleteById(user.getUserId());

        return "redirect:/user/list";
    }

    @GetMapping("/logout")
    @AllowAnonymous
    public String logout(Model model, HttpServletRequest request) {

        request.getSession().invalidate();

        return "redirect:/login";
    }
}
