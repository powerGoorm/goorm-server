package com.powerGoorm;

import com.powerGoorm.Web.argumentresolver.LoginArgumentResolver;
import com.powerGoorm.Web.interceptor.EmailcheckInterCeptor;
import com.powerGoorm.Web.interceptor.EmailcheckInterCeptor2;
import com.powerGoorm.Web.interceptor.LoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new LoginArgumentResolver());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/","/members/add","/login","/css/**","/*.ico","/error");
        registry.addInterceptor(new EmailcheckInterCeptor())
                .order(2)
                .addPathPatterns("/mail2");
                //.excludePathPatterns("/","/members/add","/login","/mail");
        registry.addInterceptor(new EmailcheckInterCeptor2())
                .order(3)
                .addPathPatterns("/passwords");
                //.excludePathPatterns("/","/members/add","/login","/mail","/mail2");
    }
}
