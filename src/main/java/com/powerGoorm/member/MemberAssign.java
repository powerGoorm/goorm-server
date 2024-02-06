package com.powerGoorm.member;


import com.powerGoorm.repositroy.MemberRepository;
import com.powerGoorm.service.MemberService;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.sql.DataSource;
import java.sql.SQLException;

@Controller
@Slf4j
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberAssign {

    //private final DataSource datasource;
    private final MemberRepository memberRepository;
    private final MemberService memberService;

    @GetMapping("/add")
    public String LoginGet(@ModelAttribute Member member){


        return "members/addMemberForm";
    }

    @PostMapping("/add")
    public String LoginPost(@Valid @ModelAttribute Member member, BindingResult bindingResult)throws SQLException{

        if(bindingResult.hasErrors()){
            log.info("회원가입 정보중에 틀린것이있습니다.");

            return "members/addMemberForm";
        }

        try{

            //MemberRepository memberRepository;
            //MemberService memberService;
            log.info("memberservice check {}",memberService.hashCode());
            memberService.savedata(member);
            memberService.logic1_findinguser("dong3058");
        }catch(SQLException e){
            throw e;
        }

        return "redirect:/";
    }




}
