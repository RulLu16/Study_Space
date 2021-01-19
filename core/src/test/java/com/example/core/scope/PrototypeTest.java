package com.example.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class PrototypeTest {

    @Test
    void prototypeBeanFind(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        PrototypeBean singletonBean1 = ac.getBean(PrototypeBean.class);
        PrototypeBean singletonBean2 = ac.getBean(PrototypeBean.class);
        Assertions.assertThat(singletonBean1).isNotSameAs(singletonBean2);
        // 두 빈이 다름. 즉 singleton과 달리 매번 새로 생성함
    }

    @Scope("prototype")
    static class PrototypeBean{

        @PostConstruct
        public void init(){
            System.out.println("init");
        }

        @PreDestroy
        public void destroy(){
            System.out.println("destroy");
        } // 스프링 컨테이너가 생성 이후 더는 관리하지 않으므로 종료 메소드가 실행되지 않음.
    }
}
