package com.powerGoorm.Web.Controller;


import com.powerGoorm.Web.service.EmailService;
import com.powerGoorm.domain.Data;
import com.powerGoorm.domain.Email.CheckUserInput;
import com.powerGoorm.domain.Email.Emails;
import com.powerGoorm.domain.Exception.Errors.CodeMistMatch.CodeMisMatch;
import com.powerGoorm.domain.Exception.Errors.CodeMistMatch.MailCodeMisMatchError;
import com.powerGoorm.domain.Exception.Errors.NoIdError.NotFoundIdError;
import com.powerGoorm.domain.Exception.Errors.NotCollectFormerror.BingdingErrorsList;
import com.powerGoorm.domain.Exception.Errors.NotCollectFormerror.NotCollectFormError;
import com.powerGoorm.domain.Exception.Errors.UnCorrectPassworderror.NotPassword;
import com.powerGoorm.domain.Exception.Errors.UnCorrectPassworderror.UnCorrectPasswordError;
import com.powerGoorm.domain.Exception.Errors.idexisterror.NoId;
import com.powerGoorm.domain.Login.Login;
import com.powerGoorm.domain.Login.PasswordDto;
import com.powerGoorm.domain.Status;
import com.powerGoorm.domain.Sucess.MakeSucessResponseWithoutData;
import com.powerGoorm.domain.Sucess.SucessResp;
import com.powerGoorm.member.MemberDto;
import com.powerGoorm.member.Member;
import com.powerGoorm.Web.repositroy.JpaMemeberRepository;
import com.powerGoorm.Web.service.LoginMemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Null;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.codec.CodecException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;
import java.util.Random;

@Slf4j
@Controller
@RequiredArgsConstructor

public class LoginForm {
    private final EmailService emailService;

    private final LoginMemberService memberService;

    private final JpaMemeberRepository jpaMemeberRepository;

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<SucessResp<Null>> PostLoginPage(@Valid @ModelAttribute Login login, BindingResult bindingResult, HttpServletRequest req) {


        if(bindingResult.hasErrors()){
            throw new NotCollectFormError("입력형식 오류발생",HttpStatus.BAD_REQUEST,new BingdingErrorsList(bindingResult.getFieldErrors()));
        }
        String id=login.getId();
        Optional<Member> member=memberService.FindByUserId(id);

        if (member.isEmpty()){

            throw new NotFoundIdError("can't found id", HttpStatus.BAD_REQUEST,new NoId());
        }


        else if(memberService.CheckPassword(id, login.getPassword())) {

            throw new UnCorrectPasswordError("un correct password",HttpStatus.BAD_REQUEST,new NotPassword());
        }

        HttpSession session=req.getSession();
        session.setAttribute("id",id);
        MakeSucessResponseWithoutData<Null> makeSucessResponseWithoutData=new MakeSucessResponseWithoutData<>();
        return makeSucessResponseWithoutData.MakeSucessResp(new Status(String.valueOf(HttpStatus.OK),"sucess"),new Data<Null>(null));

    }

    @PostMapping("/logout")
    @ResponseBody
    public ResponseEntity<SucessResp<Null>> LogOut(HttpServletRequest req){
        HttpSession session=req.getSession(false);
        String id=(String) session.getAttribute("id");
        if(session!=null){
            session.invalidate();


        }
        MakeSucessResponseWithoutData<Null> makeSucessResponseWithoutData=new MakeSucessResponseWithoutData<>();
        return makeSucessResponseWithoutData.MakeSucessResp(new Status(String.valueOf(HttpStatus.OK),"sucess"),new Data<Null>(null));
    }


    @PostMapping("/update")
    public ResponseEntity<SucessResp<Null>> PostUpdate(@Valid @ModelAttribute MemberDto memberDto,BindingResult bindingResult,HttpServletRequest req) {



        if(bindingResult.hasErrors()){
            throw new NotCollectFormError("입력형식 오류발생",HttpStatus.BAD_REQUEST,new BingdingErrorsList(bindingResult.getFieldErrors()));
        }

        HttpSession session = req.getSession(false);

        String id = (String) session.getAttribute("id");

        memberService.UpdateData(id,memberDto);

        MakeSucessResponseWithoutData<Null> makeSucessResponseWithoutData=new MakeSucessResponseWithoutData<>();
        return makeSucessResponseWithoutData.MakeSucessResp(new Status(String.valueOf(HttpStatus.OK),"sucess"),new Data<Null>(null));
    }



    @PostMapping("/mail")
    public ResponseEntity<SucessResp<Null>> SendingMail (@Valid @ModelAttribute Emails emails,BindingResult bindingResult, HttpServletRequest req){
        if(bindingResult.hasErrors()){
            throw new NotCollectFormError("입력형식 오류발생",HttpStatus.BAD_REQUEST,new BingdingErrorsList(bindingResult.getFieldErrors()));
        }

        Random r=new Random();
        char [] sendCode=new char[10];
        for(int i=0;i<10;i++){

            int k=r.nextInt(41,88);
            sendCode[i]=(char) k;

        }
        String sendCodes=String.valueOf(sendCode);
        emailService.SendMail(emails.getEmail(),"인증요청",sendCodes);

        HttpSession session=req.getSession(false);
        session.setAttribute("answer_code",sendCodes);

        MakeSucessResponseWithoutData<Null> makeSucessResponseWithoutData=new MakeSucessResponseWithoutData<>();
        return makeSucessResponseWithoutData.MakeSucessResp(new Status(String.valueOf(HttpStatus.OK),"sucess"),new Data<Null>(null));
    }


    @PostMapping("/mail2")
    public ResponseEntity<SucessResp<Null>> Certification(@ModelAttribute CheckUserInput checkUserInput, BindingResult bindingResult, HttpServletRequest req) {
        HttpSession session=req.getSession(false);
        String answer=(String)session.getAttribute("answer_code");

        if(bindingResult.hasErrors()){
            throw new NotCollectFormError("입력형식 오류발생",HttpStatus.BAD_REQUEST,new BingdingErrorsList(bindingResult.getFieldErrors()));
        } else if (answer.equals(checkUserInput.getUserinput())) {
            session.setAttribute("check","true");
            MakeSucessResponseWithoutData<Null> makeSucessResponseWithoutData=new MakeSucessResponseWithoutData<>();

            return makeSucessResponseWithoutData.MakeSucessResp(new Status(String.valueOf(HttpStatus.OK.value()),"sucess"),new Data<Null>(null));

        }
        throw new MailCodeMisMatchError("입력코드 불일치",HttpStatus.OK,new CodeMisMatch(checkUserInput.getUserinput()));


    }




    @PostMapping("/passwords")
    public ResponseEntity<SucessResp<Null>> ChangePassWord(@Valid @ModelAttribute PasswordDto passwordDto,BindingResult bindingResult, HttpServletRequest req){

        if(bindingResult.hasErrors()){
            throw new NotCollectFormError("입력형식 오류발생",HttpStatus.BAD_REQUEST,new BingdingErrorsList(bindingResult.getFieldErrors()));
        }
        HttpSession session=req.getSession(false);
        String id=(String) session.getAttribute("id");
        memberService.UpdatePassword(id, passwordDto.getPassword());
        session.setAttribute("answer",null);
        session.setAttribute("check",null);

        MakeSucessResponseWithoutData<Null> makeSucessResponseWithoutData=new MakeSucessResponseWithoutData<>();
        return makeSucessResponseWithoutData.MakeSucessResp(new Status(String.valueOf(HttpStatus.OK.value()),"sucess"),new Data<Null>(null));

    }



}
