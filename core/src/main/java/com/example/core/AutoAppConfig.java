package com.example.core;

import com.example.core.member.MemberRepository;
import com.example.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        //basePackages = "com.example.core.member" // 스캔할 시작 위치를 지정할 수 있음. 여러개 가능
        // 지정하지 않으면, componentScan이 있는 클래스의 위치가 시작 위치가 됨.
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
        // 이미 있던 AppConfig와 충돌을 피하기 위해 임의로 exclude
)
public class AutoAppConfig {

    @Bean(name = "memoryMemberRepository")
    MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    } // 수동 등록과 자동등록이 충돌하면 수동 빈 등록이 우선권을 가져 이미 생성된 자동 빈을 override
    // 하지만 최근에는 수동과 자동이 충돌하면 에러를 띄우도록 바뀌고 있음.
}
