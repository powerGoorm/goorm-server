package com.powerGoorm;


import com.powerGoorm.Web.argumentresolver.LoginCheck;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeController {

    @GetMapping("/")
    public String home(@LoginCheck String id, HttpServletRequest req){

        if(id==null){

        return "home";}


        return  "loginHome";
    }





}
