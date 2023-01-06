package com.application.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Slf4j
public class ServerErrorHandler {

    @ExceptionHandler(Exception.class)
    public ModelAndView handler(Exception e, HttpServletRequest httpServletRequest) {
        ModelAndView modelAndView = new ModelAndView("500-page");
        modelAndView.addObject("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
        modelAndView.addObject("message", e.getMessage());
        log.error("Message: {}, url: {}", e.getMessage(), httpServletRequest.getRequestURL());
        return modelAndView;
    }
}
