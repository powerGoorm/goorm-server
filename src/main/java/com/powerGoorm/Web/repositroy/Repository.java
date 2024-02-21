package com.powerGoorm.Web.repositroy;

import com.powerGoorm.member.Member;
import com.powerGoorm.member.MemberDto;

import java.util.Optional;


public interface Repository {

    void save(Member member);

    Optional<Member> findById(String username);;

    void update (String id, MemberDto memberDto);


}
