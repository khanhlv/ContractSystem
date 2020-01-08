package com.contract.interceptor;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import com.contract.annotation.AllowAnonymous;
import com.contract.consts.WebConsts;

public class RequestInterceptor extends HandlerInterceptorAdapter {
    private static final Logger logger = LoggerFactory.getLogger(RequestInterceptor.class);

    public RequestInterceptor() {
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        if (handler instanceof ResourceHttpRequestHandler) {
            return true;
        }

        Method method = ((HandlerMethod) handler).getMethod();

        if (method.isAnnotationPresent(AllowAnonymous.class)) {
            return true;
        }

//        HttpSession session = request.getSession();
//
//        String userId = (String) session.getAttribute(WebConsts.USER_ID);
//
//        if (StringUtils.isEmpty(userId)) {
//            response.sendRedirect(request.getContextPath() + "/login?returnPath=" + returnPath(request));
//
//            return false;
//        }

        return true;
    }

    private String returnPath(HttpServletRequest request) throws UnsupportedEncodingException {
        String uri = request.getRequestURI().replaceFirst(request.getContextPath(), StringUtils.EMPTY);
        String query = request.getQueryString();
        if (StringUtils.isEmpty(query)) {
            return uri;
        }
        return uri.concat("?").concat(URLEncoder.encode(query, StandardCharsets.UTF_8.toString()));
    }

}
