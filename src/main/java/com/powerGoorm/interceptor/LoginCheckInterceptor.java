package com.powerGoorm.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.Interceptor;
import org.springframework.web.servlet.HandlerInterceptor;
@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session=request.getSession(false);
        log.info("인터셉터 호출!");
        if(session!=null){
                log.info("인증된 사용자 체크완료");

        }
        else{
            String requri=request.getRequestURI();
            log.info("미인증 사용자");
            response.sendRedirect("/login?redirectURL="+requri);
        }
        return true;
    }
}
