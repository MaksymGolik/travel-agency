package com.application.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.rmi.ServerError;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class IllegalArgumentExceptionHandler {



    @ExceptionHandler(IllegalArgumentException.class)
    public ModelAndView handler(Exception e, HttpServletRequest httpServletRequest) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("code", HttpStatus.BAD_REQUEST.value());
        modelAndView.addObject("message", e.getMessage());
        log.error("Message: {}, url: {}", e.getMessage(), httpServletRequest.getRequestURL());
        return modelAndView;
    }

  /*  public ModelAndView handler(Exception e, HttpServletRequest httpServletRequest) {
        log.error("Message: {}, url: {}", e.getMessage(), httpServletRequest.getRequestURL());
        Map<String, String> modelMessage = new HashMap<>();
        modelMessage.put("error", e.getMessage());
        ModelAndView modelAndView = new ModelAndView("bad-request",modelMessage, HttpStatus.BAD_REQUEST);

        return modelAndView;
    }
*/

}
