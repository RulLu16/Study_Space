package com.example.core;

import com.example.core.discount.DiscountPolicy;
import com.example.core.discount.FixDiscountPolicy;
import com.example.core.discount.RateDiscountPolicy;
import com.example.core.member.MemberRepository;
import com.example.core.member.MemberService;
import com.example.core.member.MemberServiceImpl;
import com.example.core.member.MemoryMemberRepository;
import com.example.core.order.OrderService;
import com.example.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean // 스프링 컨테이너에 등록됨
    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
    } // 생성자를 통해 주입

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    } // 이렇게 주입하는 역할을 부여하므로써 DIP를 완성

    @Bean
    public DiscountPolicy discountPolicy(){
        // return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    } // 이제 이곳만 고치면 구현체를 바꿀 수 있게됨.
}
