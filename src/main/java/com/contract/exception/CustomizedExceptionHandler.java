package com.contract.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomizedExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomizedExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public String handleError(Exception ex) {
        LOGGER.error("Exception", ex);

        return "error/500";
    }


    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessDenied(Model model, AccessDeniedException ex) {
        LOGGER.error("AccessDeniedException", ex);

        return "exception/accessDenied";
    }

    @ExceptionHandler(SystemException.class)
    public String handleException(Model model, SystemException ex) {
        LOGGER.error("SystemException", ex);

        model.addAttribute("timestamp", System.currentTimeMillis());
        model.addAttribute("message", ex.getMessage());

        return "exception/system";
    }

}
