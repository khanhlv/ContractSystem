package com.contract.controller;

import com.contract.annotation.AllowAnonymous;
import com.contract.consts.WebConsts;
import com.contract.form.LoginForm;
import com.contract.model.User;
import com.contract.model.UserPermission;
import com.contract.service.CompanyService;
import com.contract.service.UserPermissionService;
import com.contract.service.UserService;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private UserPermissionService userPermissionService;

    @GetMapping("/login")
    @AllowAnonymous
    public String home(LoginForm loginForm, Model model) {

        model.addAttribute("form", loginForm);
        model.addAttribute("companyList", companyService.findByStatus(1L));

        return "login/index";
    }

    @PostMapping("/doLogin")
    @AllowAnonymous
    public String doLogin(LoginForm loginForm, Model model, HttpServletRequest request) {

        model.addAttribute("form", loginForm);

        User user = userService.checkLogin(loginForm.getUsername(),
                loginForm.getPassword(),
                NumberUtils.createLong(loginForm.getCompany()));

        if (user == null) {
            model.addAttribute("messsage", "Tên đăng nhập, mật khẩu hoặc công ty không đúng.");
            model.addAttribute("companyList", companyService.findByStatus(1L));

            return "login/index";
        }

        if (user.getStatus().compareTo(0L) == 0) {
            model.addAttribute("messsage", "Tài khoản của đã bị khóa.");
            model.addAttribute("companyList", companyService.findByStatus(1L));

            return "login/index";
        }

        request.getSession(true).setAttribute(WebConsts.USER_ID, user.getUserId());
        request.getSession(true).setAttribute(WebConsts.FULL_NAME, user.getFullName());
        request.getSession(true).setAttribute(WebConsts.EMAIL, user.getEmail());
        request.getSession(true).setAttribute(WebConsts.COMPANY, user.getCompany());

        List<UserPermission> userPermissionList = userPermissionService.findAllWithUserGroup(user.getUserGroup());

        List<String> permissionList = new ArrayList<>();
        userPermissionList.stream().forEach(v -> {
            permissionList.add(v.getModule().getModuleUrl());
        });

        request.getSession(true).setAttribute(WebConsts.USER_PERMISSION, permissionList);

        return "redirect:" + loginForm.getReturnPath();
    }
}
