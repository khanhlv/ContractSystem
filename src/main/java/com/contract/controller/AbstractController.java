package com.contract.controller;

import com.contract.consts.WebConsts;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;

@Controller
public abstract class AbstractController {
    protected String getUserId(HttpServletRequest request) {
        return (String) request.getSession(false).getAttribute(WebConsts.USER_ID);
    }

}
