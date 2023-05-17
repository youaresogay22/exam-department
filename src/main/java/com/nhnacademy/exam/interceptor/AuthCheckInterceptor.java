package com.nhnacademy.exam.interceptor;

import com.nhnacademy.exam.exception.UnauthorizedUserException;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String userId = request.getHeader("X-USER-ID");
        if(!StringUtils.hasText(userId) || !userId.equalsIgnoreCase("nhnacademy") ){
            throw new UnauthorizedUserException();
        }
        return true;
    }

}
