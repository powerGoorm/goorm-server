package com.powerGoorm.Web.Controller;


import com.powerGoorm.Web.repositroy.JpaMemeberRepository;
import com.powerGoorm.Web.service.LoginMemberService;
import com.powerGoorm.member.Member;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.time.LocalDateTime;

@Controller
@Slf4j
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberAssign {


    private final JpaMemeberRepository jpaMemeberRepository;
    private final LoginMemberService memberService;

    @GetMapping("/add")
    public String LoginGet(@ModelAttribute Member member){


        return "members/addMemberForm";
    }


    @PostMapping("/add")
    public String LoginPost(@Valid @ModelAttribute Member member, BindingResult bindingResult)throws SQLException{

        if(bindingResult.hasErrors()){
            log.info("회원가입 정보중에 틀린것이있습니다.");

            return "members/addMemberForm";
        } else if (memberService.FindByUserId(member.getId())!=null) {
            log.info("이미존재하는 아이디입니다.");
            return  "members/addMemberForm";
        }
        LocalDateTime now = LocalDateTime.now();

        member.setCreate_at(now.toString());
        memberService.SaveData(member);
        /*try{

            MemberRepository memberRepository;
            MemberService memberService;
            log.info("memberservice check {}",memberService.hashCode());
            memberService.savedata(member);
            memberService.logic1_findinguser("dong3058");


        }catch(SQLException e){
            throw e;
        }*/

       return  "redirect:/";
    }






}
