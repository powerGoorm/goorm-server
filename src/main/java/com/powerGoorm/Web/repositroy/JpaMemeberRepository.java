package com.powerGoorm.Web.repositroy;


import com.powerGoorm.member.MemberDto;
import com.powerGoorm.member.Member;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.function.Supplier;

@RequiredArgsConstructor
@Repository
@Slf4j
public class JpaMemeberRepository implements com.powerGoorm.Web.repositroy.Repository {

    private final EntityManager em;

    @Override
    public void save(Member member) {
        em.persist(member);

    }

    @Override
    public void update(String id, MemberDto memberDto) {

        Member member=em.find(Member.class,id);
        member.setGit(memberDto.getGit());
        member.setIntroduction(memberDto.getIntroduction());


    }

    @Override
    public Optional<Member> findById(String id) {

        Member member=em.find(Member.class,id);
        log.info("{} check",member);


        return Optional.ofNullable(member);
    }

    public boolean CheckPassword(String id ,String password){
        Member member=em.find(Member.class,id);
        return !member.getPassword().equals(password);
    }
    public void UpdatePassword(String id,String password){
        Member member=em.find(Member.class,id);
        member.setPassword(password);

    }
}
