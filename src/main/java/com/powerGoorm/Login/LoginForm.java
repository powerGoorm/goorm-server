package com.powerGoorm.Login;


import com.powerGoorm.member.Member;
import com.powerGoorm.service.LoginService;
import com.powerGoorm.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.SQLException;

@Slf4j
@Controller
@RequiredArgsConstructor

public class LoginForm {

    private final LoginService loginService;
    @GetMapping("/login")
    public String loginfromget(@ModelAttribute Login login){

        return "login/loginForm";
    }

    @PostMapping("/login")
    public String loginfrompost(@Valid @ModelAttribute Login login , BindingResult bindingResult, HttpServletRequest req) {
        if (bindingResult.hasErrors()) {

            log.info("로그인 입력정보가 형식에 맞지않습니다");
            return "redirect:/login";
        }

        boolean x=loginService.logincheck(login.getId());
        if(x){

            HttpSession session=req.getSession();
            session.setAttribute("id",login.getId());
            log.info("login sucesssed");
            return "redirect:/";
        }
        else{
            log.info("로그인 단계에서 없는 유저 다시 로그인 페이지로 반환");
            return "redirect:/login";
        }

    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest req){
        HttpSession session=req.getSession(false);
        log.info("session check:{}",session);
        if(session!=null){
            session.invalidate();
            log.info("session delete");
            log.info("session check:{}",session);

        }

        return "redirect:/";
    }
}
