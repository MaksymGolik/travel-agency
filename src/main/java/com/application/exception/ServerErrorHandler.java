package com.application.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class ServerErrorHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView entityNotFoundHandler(Exception e, HttpServletRequest httpServletRequest) {
        log.error("Message: {}, url: {}", e.getMessage(), httpServletRequest.getRequestURL());
        Map<String, String> modelMessage = new HashMap<>();
        modelMessage.put("info", e.getMessage());
        ModelAndView modelAndView = new ModelAndView("error", modelMessage, HttpStatus.NOT_FOUND );

        return modelAndView;
    }

    @ExceptionHandler({IllegalArgumentException.class, ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ModelAndView illegalArgumentHandler(Exception e, HttpServletRequest httpServletRequest) {
        log.error("Message: {}, url: {}", e.getMessage(), httpServletRequest.getRequestURL());
        Map<String, String> modelMessage = new HashMap<>();
        modelMessage.put("info", e.getMessage());
        ModelAndView modelAndView = new ModelAndView("error", modelMessage, HttpStatus.BAD_REQUEST );

        return modelAndView;
    }
}
