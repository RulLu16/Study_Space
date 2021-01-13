package com.example.core.scan;

import com.example.core.AutoAppConfig;
import com.example.core.member.MemberService;
import com.example.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutoAppConfigTest {

    @Test
    void basicScan(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

        MemberService memberService = ac.getBean("memberServiceImpl",MemberService.class);
        // 생성되는 빈의 이름에 주의! componentscan의 기본 빈 이름은 클래스명.

        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);
    }
}
