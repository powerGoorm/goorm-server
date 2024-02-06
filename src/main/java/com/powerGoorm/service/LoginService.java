package com.powerGoorm.service;


import com.powerGoorm.repositroy.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;


    @Transactional
    public boolean logincheck(String id){
        if(memberRepository.findbyusername(id)){
            return true;
        }
        else{
            return false;
        }

    }
}
