package com.application.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;



@ControllerAdvice
@Slf4j
public class ServerErrorHandler {

    @ExceptionHandler(Exception.class)
    public ModelAndView handler(Exception e, HttpServletRequest httpServletRequest) {
        System.out.println("Problem" + e.getMessage());
        log.error("Message: {}, url: {}", e.getMessage(), httpServletRequest.getRequestURL());
        Map<String, String> modelMessage = new HashMap<>();
        modelMessage.put("error", e.getMessage());
        ModelAndView modelAndView = new ModelAndView("500-page",modelMessage, HttpStatus.INTERNAL_SERVER_ERROR);

        return modelAndView;
    }
}
