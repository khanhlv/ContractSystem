package com.contract.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;

import com.contract.consts.WebConsts;
import com.contract.model.Company;

@Controller
public abstract class AbstractController {
    protected Long getUserId(HttpServletRequest request) {
        return (Long) request.getSession(false).getAttribute(WebConsts.USER_ID);
    }

    protected Company getCompany(HttpServletRequest request) {
        return (Company) request.getSession(false).getAttribute(WebConsts.COMPANY);
    }
}
