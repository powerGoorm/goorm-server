package com.powerGoorm.Web.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
@Slf4j
public class EmailcheckInterCeptor implements HandlerInterceptor{

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session=request.getSession(false);

        if(session.getAttribute("answer_code")==null){

            response.sendRedirect("/mail?redirectURL="+request.getRequestURI());
            return false;
        }
        return true;
    }
}
