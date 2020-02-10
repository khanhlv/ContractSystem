package com.contract.exception;

import java.text.SimpleDateFormat;
import java.util.Date;

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

    @ExceptionHandler(NotFoundDBException.class)
    public String handleAccessDenied(Model model, NotFoundDBException ex) {
        LOGGER.error("NotFoundDBException", ex);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        model.addAttribute("timestamp", simpleDateFormat.format(new Date()));
        model.addAttribute("message", ex.getMessage());

        return "exception/notFoundDB";
    }

    @ExceptionHandler(SystemException.class)
    public String handleException(Model model, SystemException ex) {
        LOGGER.error("SystemException", ex);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        model.addAttribute("timestamp", simpleDateFormat.format(new Date()));
        model.addAttribute("message", ex.getMessage());

        return "exception/system";
    }

}
