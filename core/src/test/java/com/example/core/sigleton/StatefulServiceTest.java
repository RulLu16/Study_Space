package com.example.core.sigleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class StatefulServiceTest {

    @Test
    void statefulServiceSingleton(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //thread A: 10000원 주문
        int price1 = statefulService1.order("userA", 10000);

        //thread B: 20000원 주문
        int price2 = statefulService2.order("userB", 20000);

        //threadA: 주문 금액 조회
        //int price = statefulService1.getPrice();
        Assertions.assertThat(price1).isNotEqualTo(price2);
    }

    static class TestConfig{
        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }
}
