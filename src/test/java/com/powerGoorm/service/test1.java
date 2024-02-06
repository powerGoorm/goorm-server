package com.powerGoorm.service;

import com.powerGoorm.member.Member;
import com.powerGoorm.repositroy.MemberRepository;
import com.powerGoorm.service.MemberService;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;


import javax.sql.DataSource;


import java.sql.SQLException;
import java.util.Date;

@SpringBootTest


public class test1 {
        @Autowired
        private MemberRepository memberRepository;
        @Autowired
        private MemberService memberService;
        @TestConfiguration
        static class TestConfig{

            private final DataSource dataSource;
            public TestConfig(DataSource dataSource) {
                this.dataSource = dataSource;
            }


            @Bean
            MemberRepository memberRepository(){
                return new MemberRepository(dataSource);
            }

            @Bean
            MemberService memberService(){
                return new MemberService(memberRepository());
            }



        }

        @Test
        void test() throws SQLException {
            Date today=new Date();
            Member member=new Member("dong3058","황동근","1234","url","helloworld");
            memberRepository.save(member);
            memberService.logic1_findinguser("dong3058");
            Member member2=new Member("dong338","황동근","1234567","url2","helloworlds");
            memberService.updatedata(member2);
            memberService.logic1_findinguser("dong338");
            memberService.deletedata("dong338");
        }








}
