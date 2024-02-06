package com.powerGoorm.service;


import com.fasterxml.classmate.MemberResolver;
import com.powerGoorm.member.Member;
import com.powerGoorm.repositroy.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.processing.SQL;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;


    /*public MemberService(MemberRepository memrepo){
        this.memberRepository=memrepo;
    }*/

    @Transactional
    public  boolean logic1_findinguser(String id){
        try {
            memberRepository.findbyusername(id);
            log.info("memberrepo {}",memberRepository.hashCode());
            return true;

        }
        catch(Exception e){
            log.info("error notfound user");
            return false;
        }
        finally{
            log.info("serach_service end");
        }
    }
    @Transactional
    public void savedata(Member member) throws SQLException{
        try{
            memberRepository.save(member);
        }
        catch(SQLException e){
            throw e;
        }
        finally{
            log.info("save end");
        }

    }
    @Transactional
    public void updatedata(Member member) throws SQLException{
        try{
            memberRepository.save(member);
        }
        catch(SQLException e){
            throw e;
        }
        finally{
            log.info("save end");
        }

    }
    @Transactional
    public void deletedata(String id) throws SQLException{
        try{
            memberRepository.delete(id);
        }
        catch(SQLException e){
            throw e;
        }
        finally{
            log.info("delete end");
        }

    }


}
