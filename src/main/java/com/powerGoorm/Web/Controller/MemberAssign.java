package com.powerGoorm.Web.Controller;


import com.powerGoorm.Web.repositroy.JpaMemeberRepository;
import com.powerGoorm.Web.service.LoginMemberService;
import com.powerGoorm.domain.Data;
import com.powerGoorm.domain.Exception.Errors.NotCollectFormerror.BingdingErrorsList;
import com.powerGoorm.domain.Exception.Errors.NotCollectFormerror.NotCollectFormError;
import com.powerGoorm.domain.Exception.Errors.idexisterror.NoId;
import com.powerGoorm.domain.Exception.Errors.idexisterror.IdExistError;
import com.powerGoorm.domain.Status;
import com.powerGoorm.domain.Sucess.MakeSucessResponseWithoutData;
import com.powerGoorm.domain.Sucess.SucessResp;
import com.powerGoorm.member.Member;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Null;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@Slf4j
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberAssign {


    private final JpaMemeberRepository jpaMemeberRepository;
    private final LoginMemberService memberService;


    @ResponseBody
    @PostMapping("/add")
    public ResponseEntity<SucessResp<Null>> LoginPost(@Valid @ModelAttribute Member member, BindingResult bindingResult){
        //Member member=m.getBody();
        if(bindingResult.hasErrors()){
            throw new NotCollectFormError("입력형식 오류발생",HttpStatus.BAD_REQUEST,new BingdingErrorsList(bindingResult.getFieldErrors()));
        }

       if(memberService.FindByUserId(member.getId()).isPresent()) {

            throw new IdExistError("이미 존재하는 아이디입니다.",HttpStatus.BAD_REQUEST,new NoId());
        }
        LocalDateTime now = LocalDateTime.now();
        member.setCreate_at(now.toString());
        member.setDelete_at(null);
        memberService.SaveData(member);
        MakeSucessResponseWithoutData<Null> makeSucessResponseWithoutData=new MakeSucessResponseWithoutData<>();
        return makeSucessResponseWithoutData.MakeSucessResp(new Status(String.valueOf(HttpStatus.OK.value()),"sucess"),new Data<Null>(null));
    }




}
