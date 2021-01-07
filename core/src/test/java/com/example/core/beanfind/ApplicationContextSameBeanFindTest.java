package com.example.core.beanfind;

import com.example.core.AppConfig;
import com.example.core.discount.DiscountPolicy;
import com.example.core.member.MemberRepository;
import com.example.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Member;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextSameBeanFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);

    @Test
    @DisplayName("타입 조회 시 같은 타입이 둘 이상일 경우 중복 오류")
    void findBeanByTypeDuplicate(){
        //MemberRepository memberRepository = ac.getBean(MemberRepository.class);
        assertThrows(NoUniqueBeanDefinitionException.class,
                ()->ac.getBean(MemberRepository.class));
    }

    @Test
    @DisplayName("타입 조회 시 같은 타입이 둘 이상일 경우 빈 이름 지정해 오류를 피한다")
    void findBeanByName(){
        MemberRepository memberRepository = ac.getBean("memberRepository1",MemberRepository.class);
        assertThat(memberRepository).isInstanceOf(MemberRepository.class);
    }

    @Test
    @DisplayName("특정 타입 모두 조회")
    void findAllBeanByType(){
        Map<String, MemberRepository> beanOfType = ac.getBeansOfType(MemberRepository.class);
        assertThat(beanOfType.size()).isEqualTo(2);
    }

    @Configuration
    static class SameBeanConfig{

        @Bean
        public MemberRepository memberRepository1(){
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberRepository memberRepository2(){
            return new MemoryMemberRepository();
        }
    }
}
