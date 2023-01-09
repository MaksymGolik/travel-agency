package com.application.exception;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAccessDeniedHandler  implements AccessDeniedHandler {

    @ExceptionHandler(CustomAccessDeniedException.class)

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException ade) throws IOException, ServletException {

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("error.html");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        request.setAttribute("code","403 / Forbidden");
        request.setAttribute("info", ade.getMessage());
        requestDispatcher.forward(request, response);

    }
}