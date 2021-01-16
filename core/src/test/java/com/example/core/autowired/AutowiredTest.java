package com.example.core.autowired;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.example.core.member.Member;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean{

        @Autowired(required = false)
        public void setNoBean1(Member member){
            // 없으면 메소드 자체가 호출 x
        }
        
        @Autowired
        public void setNoBean2(@Nullable Member member){
            // 없으면 null이 들어감
        }

        @Autowired
        public void setNoBean3(Optional<Member> member){
            // 없으면 Optional.empty를 넣음
        }
    }
}
