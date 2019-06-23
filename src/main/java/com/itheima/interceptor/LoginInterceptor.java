package com.itheima.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        Object user = request.getSession().getAttribute("user");
        if (user==null){
            response.sendRedirect(request.getContextPath()+"/jsp/login.jsp");
            return false;
        }
        return true;
    }

}
