package com.powerGoorm.Web.service;


import com.powerGoorm.member.Member;
import com.powerGoorm.Web.repositroy.JpaMemeberRepository;
import com.powerGoorm.member.MemberDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginMemberService {

    private final JpaMemeberRepository jpaMemeberRepository;



    @Transactional
    public Optional<Member> FindByUserId(String username){

            return jpaMemeberRepository.findById(username);


    }
    @Transactional
    public boolean CheckPassword(String username,String password){

     return jpaMemeberRepository.CheckPassword(username,password);


    }
    @Transactional
    public void SaveData(Member member) {
        jpaMemeberRepository.save(member);

    }
    @Transactional
    public void UpdateData(String username, MemberDto memberDto){

        jpaMemeberRepository.update(username,memberDto);
    }



    @Transactional
    public void UpdatePassword(String username,String password){

        jpaMemeberRepository.UpdatePassword(username,password);

    }






}
