package com.example.spring;

import com.example.spring.repository.MemberRepository;
import com.example.spring.repository.MemoryMemberRepository;
import com.example.spring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 자바 코드로 직접 스프링 빈 등록
public class SpringConfig {

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
    // 설정파일을 둘 경우, 상황에 따라 구현 클래스를 변경해야 할 때 매우 편리
}
