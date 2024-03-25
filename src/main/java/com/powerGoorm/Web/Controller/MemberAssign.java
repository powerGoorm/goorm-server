package com.powerGoorm.Web.Controller;


import com.powerGoorm.Web.repositroy.JpaMemeberRepository;
import com.powerGoorm.Web.service.LoginMemberService;
import com.powerGoorm.domain.Data;
import com.powerGoorm.domain.Exception.Errors.NotCollectFormerror.BingdingErrorsList;
import com.powerGoorm.domain.Exception.Errors.idexisterror.IdExistError;
import com.powerGoorm.domain.Exception.Errors.idexisterror.NoId;
import com.powerGoorm.domain.Status;
import com.powerGoorm.domain.Sucess.MakeSucessResponseWithoutData;
import com.powerGoorm.domain.Sucess.SucessResp;
import com.powerGoorm.member.Member;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Null;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Slf4j
@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberAssign {


    private final JpaMemeberRepository jpaMemeberRepository;
    private final LoginMemberService memberService;


    @ResponseBody
    @PostMapping("/add")
    public ResponseEntity<SucessResp<Null>> LoginPost(@Valid @RequestBody Member m,BindingResult bindingResult){


        if (bindingResult.hasErrors()){
            FieldError b=bindingResult.getFieldError();
            throw new IdExistError(b.getDefaultMessage(),HttpStatus.BAD_REQUEST,new NoId());

        }
        Optional<Member> member=memberService.FindByUserId(m.getId());
        if(member.isPresent()){
            throw new IdExistError("이미 존재하는 아이디입니다.",HttpStatus.BAD_REQUEST,new NoId());
        }


        LocalDateTime date=LocalDateTime.now();
        m.setCreatedAt(date);
        memberService.SaveData(m);
        MakeSucessResponseWithoutData<Null> makeSucessResponseWithoutData=new MakeSucessResponseWithoutData<>();
        return makeSucessResponseWithoutData.MakeSucessResp(new Status(String.valueOf(HttpStatus.OK.value()),"sucess"),new Data<Null>(null));
    }




}
