package com.powerGoorm.Web.interceptor;

import com.powerGoorm.domain.Exception.Errors.SessionEnderror.Redirecturl;
import com.powerGoorm.domain.Exception.Errors.SessionEnderror.SessionEndError;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;
@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session=request.getSession(false);


        if(session==null){
            String requri=request.getRequestURI();
            throw new SessionEndError("no session or session end", HttpStatus.BAD_REQUEST,new Redirecturl("/login?redirectURL="+requri));

        }


        return true;
    }
}
