package com.powerGoorm.Web.Controller;


import com.powerGoorm.domain.Email.CheckUserInput;
import com.powerGoorm.domain.Email.Emails;
import com.powerGoorm.Web.service.EmailService;
import com.powerGoorm.domain.Login.Login;
import com.powerGoorm.member.MemberDto;
import com.powerGoorm.member.Member;
import com.powerGoorm.domain.Login.PasswordDto;
import com.powerGoorm.Web.repositroy.JpaMemeberRepository;
import com.powerGoorm.Web.service.LoginMemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;
import java.util.Random;

@Slf4j
@Controller
@RequiredArgsConstructor

public class LoginForm {
    private final EmailService emailService;

    private final LoginMemberService memberService;

    private final JpaMemeberRepository jpaMemeberRepository;
    @GetMapping("/login")
    public String GetLoginPage(@ModelAttribute Login login){

        return "login/loginForm";
    }

    @PostMapping("/login")
    public String PostLoginPage(@Validated @ModelAttribute Login login, BindingResult bindingResult, HttpServletRequest req) {


        String id=login.getId();

        Optional<Member> member=memberService.FindByUserId(id);

        if (member==null){
            bindingResult.reject("존재하지않는 아이디입니다.");
        } else if (memberService.CheckPassword(id, login.getPassword())) {
            return "/login/loginForm";
        }

        if(bindingResult.hasErrors()){

            return "/login/loginForm";

        }

        HttpSession session=req.getSession();
        session.setAttribute("id",id);
        log.info("login 완료");
        return "redirect:/";

    }

    @PostMapping("/logout")
    public String LogOut(HttpServletRequest req){
        HttpSession session=req.getSession(false);

        if(session!=null){
            session.invalidate();


        }

        return "redirect:/";
    }

    @GetMapping("/update")
    public String GetUpdate(@ModelAttribute MemberDto memberDto){


        return "update";
    }
    @PostMapping("/update")
    public String PostUpdate(@Valid @ModelAttribute MemberDto memberDto, BindingResult bindingResult, HttpServletRequest req){

        if(bindingResult.hasErrors()){

            return  "update";
        }

        HttpSession session=req.getSession(false);
        if(session==null){

            return "redirect:/";
        }
        String id=(String)session.getAttribute("id");

        memberService.UpdateData(id,memberDto);
        log.info("업데이트 완료");
        return "redirect:/";
    }


    @GetMapping("/mail")
    public String GetMail(@ModelAttribute Emails emails,HttpServletRequest req){


        return "members/mail";
    }

    @PostMapping("/mail")
    public String PostMail(@Valid @ModelAttribute Emails emails,BindingResult bindingResult,HttpServletRequest req){
        if(bindingResult.hasErrors()){
            return "members/mail";
        }

        Random r=new Random();
        char [] sendCode=new char[10];
        for(int i=0;i<10;i++){


            sendCode[i]=Character.forDigit(r.nextInt(100),10);;
        }
        String sendCodes=String.valueOf(sendCode);
        emailService.SendMail(emails.getEmail(),"인증요청",sendCodes);

        HttpSession session=req.getSession(false);
        session.setAttribute("answer_code",sendCodes);

        return "redirect:/mail2";
    }

    @GetMapping("/mail2")
        public String GetCertification(@ModelAttribute CheckUserInput checkUserInput) {

        return "members/mail2";
    }
    @PostMapping("/mail2")
    public String PostCertification(@ModelAttribute CheckUserInput checkUserInput, BindingResult bindingResult, HttpServletRequest req) {
        HttpSession session=req.getSession(false);
        String answer=(String)session.getAttribute("answer_code");
        log.info("answr{} userenter{}",answer,checkUserInput.getUserinput());
        if(bindingResult.hasErrors()){


            return "members/mail2";
        } else if (answer.equals(checkUserInput.getUserinput())) {
            session.setAttribute("check","true");
            return "redirect:/passwords";

        }
        return "members/mail2";


    }

    @GetMapping("/passwords")
    public String GetUpdatePassword(@ModelAttribute PasswordDto passwordDto, HttpServletRequest req){

        return "login/password";
    }


    @PostMapping("/passwords")
    public String PostUpdatePasssword(@Valid @ModelAttribute PasswordDto passwordDto, BindingResult bindingResult, HttpServletRequest req){

        if(bindingResult.hasErrors()){


            return "login/password";
        }
        HttpSession session=req.getSession(false);
        String id=(String) session.getAttribute("id");
        memberService.UpdatePassword(id, passwordDto.getPassword());
        session.setAttribute("answer",null);
        session.setAttribute("check",null);

        return "redirect:/";

    }



}
