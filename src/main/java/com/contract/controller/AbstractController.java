package com.contract.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;

import com.contract.consts.WebConsts;
import com.contract.model.User;

@Controller
public abstract class AbstractController {
    protected Long getUserId(HttpServletRequest request) {
        return (Long) request.getSession(false).getAttribute(WebConsts.USER_ID);
    }

    protected User getUser(HttpServletRequest request) {
        return (User) request.getSession(false).getAttribute(WebConsts.USER);
    }

}
