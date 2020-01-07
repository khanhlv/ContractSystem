package com.contract.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;

import com.contract.consts.WebConsts;

@Controller
public class AbstractController {
    protected String getUserId(HttpServletRequest request) {
        return (String) request.getSession(false).getAttribute(WebConsts.USER_ID);
    }

}
