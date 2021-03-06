package com.example.core.scope;

import ch.qos.logback.core.net.server.Client;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.inject.Provider;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        PrototypeBean bean = ac.getBean(PrototypeBean.class);
        bean.addCount();
        Assertions.assertThat(bean.getCount()).isEqualTo(1);

        PrototypeBean bean2 = ac.getBean(PrototypeBean.class);
        bean2.addCount();
        Assertions.assertThat(bean.getCount()).isEqualTo(1);
    }

    @Test
    void singletonClientUsePrototype(){
        ApplicationContext ac =
                new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);

        ClientBean bean1 = ac.getBean(ClientBean.class);
        int count1 = bean1.logic();
        Assertions.assertThat(count1).isEqualTo(1);

        /*ClientBean bean2 = ac.getBean(ClientBean.class);
        int count2 = bean2.logic();
        Assertions.assertThat(count2).isEqualTo(2);
        // prototype bean의 값이 변경된것이지만, 이는 이미 주입 완료된 bean이므로 새로 만들지 않음.*/

        ClientBean bean2 = ac.getBean(ClientBean.class);
        int count2 = bean2.logic();
        Assertions.assertThat(count2).isEqualTo(1);
        // objectprovider를 통해 프로토타입 빈을 새로 찾아주므로 프로토타입의 의도대로 사용 가능
        // 이렇게 찾아주는 것을 DL이라 한다
    }

    static class ClientBean{
        //private final PrototypeBean prototypeBean; // 생성 시점에 주입

        /*@Autowired
        private ObjectProvider<PrototypeBean> prototypeBeanProvider;
        // objectprovider를 사용해 DL
         */

        @Autowired // JSR303 provider를 사용해 DL
        private Provider<PrototypeBean> prototypeBeanProvider;

        public int logic(){
            PrototypeBean prototypeBean = prototypeBeanProvider.get();
                    //.getObject();
            // 스프링 컨테이너를 통해 해당 빈을 찾아서 반환 => DL
            prototypeBean.addCount();

            return prototypeBean.getCount();
        }
    }

    @Scope("prototype")
    static class PrototypeBean{
        private int count = 0;

        public void addCount(){
            count++;
        }

        public int getCount(){
            return count;
        }
    }
}
