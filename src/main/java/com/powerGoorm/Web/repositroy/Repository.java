package com.powerGoorm.Web.repositroy;

import com.powerGoorm.member.MemberDto;
import com.powerGoorm.member.Member;

import java.util.Optional;


public interface Repository {

    void save(Member member);

    Optional<Member> findById(String id);;

    void update(String id, MemberDto memberDto);

    //void delete(Member member);
}
