package com.powerGoorm.Web.repositroy;


import com.powerGoorm.member.Member;
import com.powerGoorm.member.MemberDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
@Slf4j
public class JpaMemeberRepository implements com.powerGoorm.Web.repositroy.Repository {

    private final EntityManager em;

    @Override
    public void save(Member m) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password=encoder.encode(m.getPassword());
        m.setPassword(password);

        em.persist(m);



    }

    @Override
    public void update(String username, MemberDto memberDto) {
        String query="update Member m set m.git =:git,m.intro=:intro  where m.name=:username";
       em.createQuery(query)
               .setParameter("git",memberDto.getGit())
               .setParameter("intro",memberDto.getIntro())
               .setParameter("username",username)
               .executeUpdate();




    }

    @Override
    public Optional<Member> findById(String username) {
        try{
        String query="select m from Member m where m.name=:username";
        Member m=em.createQuery(query,Member.class)
                .setParameter("username",username)
                .getSingleResult();
        return Optional.ofNullable(m);}
        catch(NoResultException e){

            return Optional.ofNullable(null);
        }

    }

    public boolean CheckPassword(String username ,String password){
        try{
        String query="select m from Member m where m.name=:username";
        Member member=em.createQuery(query,Member.class)
                .setParameter("username",username)
                .getSingleResult();

        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();

        return !encoder.matches(password,member.getPassword());}
        catch(NoResultException e){
            return true;
        }

    }
    public void UpdatePassword(String username,String password){
        String query="update Member m set m.password=:password where m.name=:username";
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        String hashedpassword=encoder.encode(password);
        em.createQuery(query)
                        .setParameter("password",hashedpassword)
                                .setParameter("username",username)
                                        .executeUpdate();


    }
}
