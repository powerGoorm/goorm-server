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
    public Optional<Member> FindByUserId(String id){

            return jpaMemeberRepository.findById(id);


    }
    @Transactional
    public boolean CheckPassword(String id,String password){

     return jpaMemeberRepository.CheckPassword(id,password);


    }
    @Transactional
    public void SaveData(Member member) {
        jpaMemeberRepository.save(member);

    }
    @Transactional
    public void UpdateData(String id, MemberDto memberDto){

        jpaMemeberRepository.update(id,memberDto);
    }



    @Transactional
    public void UpdatePassword(String id,String password){

        jpaMemeberRepository.UpdatePassword(id,password);

    }






}
