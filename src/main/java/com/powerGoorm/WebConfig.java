package com.powerGoorm;


import com.powerGoorm.Web.interceptor.EmailcheckInterCeptor;
import com.powerGoorm.Web.interceptor.EmailcheckInterCeptor2;
import com.powerGoorm.Web.interceptor.LoginCheckInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/","/members/add","/login","/css/**","/*.ico","/error");
        registry.addInterceptor(new EmailcheckInterCeptor())
                .order(2)
                .addPathPatterns("/mail2");

        registry.addInterceptor(new EmailcheckInterCeptor2())
                .order(3)
                .addPathPatterns("/passwords");

    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("*")
                        .allowedHeaders("Authorization", "Content-Type");
            }
        };
    }
}
