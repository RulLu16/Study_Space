package com.example.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close();
        // 스프링빈은 객체를 먼저 생성하고 의존관계를 주입하는 라이프사이클을 가진다.
        // 생성자 자동주입의 경우에는 같이 일어나지만 보통 위와 같은 라이프사이클이다.
        // 스프링 컨테이너 생성 -> 스프링 빈 생성 -> 의존관계 주입 -> 초기화 콜백 -> 사용
        // -> 소멸 전 콜백 -> 스프링 종료 의 이벤트 라이프사이클이다.
    }

    @Configuration
    static class LifeCycleConfig{

        @Bean//(initMethod = "init", destroyMethod = "close") // init과 close 메소드를 지정할 수 있음
        // destroyMethod는 특이하게 close를 명시하지 않아도 close, shutdown등의 이름 메소드를 추론하여 호출한다.
        public NetworkClient networkClient(){
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello");

            return networkClient;
        }
    }
}
