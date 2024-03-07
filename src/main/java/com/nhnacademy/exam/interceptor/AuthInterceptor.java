package com.nhnacademy.exam.interceptor;

import com.nhnacademy.exam.exception.UserAuthenticationFailedException;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        if (request.getHeader("X-USER-ID") == null || !request.getHeader("X-USER-ID").equals("nhnacademy")) {
            System.out.println(request.getHeader("X-USER-ID"));
            throw new UserAuthenticationFailedException();
        }

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }
}
