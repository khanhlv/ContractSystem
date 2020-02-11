package com.contract.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/context")
public class ContextController implements ApplicationContextAware {

    private ApplicationContext context;

    private String user = "khanhlv";

    private String pass = "20111991";

    @GetMapping("/shutdown")
    public void shutdown(
            @RequestParam(name = "username") String username,
            @RequestParam(name = "password") String password) {
        if (StringUtils.equals(user, username) && StringUtils.equals(pass, password)) {
            ((ConfigurableApplicationContext) context).close();
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        this.context = ctx;
    }
}
