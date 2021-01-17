package com.example.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class NetworkClient /*implements InitializingBean, DisposableBean*/ { // 임시 네트워크

    private String url;

    public NetworkClient() {
        System.out.println("call url");

    }

    public void setUrl(String url) {
        this.url = url;
    }

    // call when service start
    public void connect() {
        System.out.println("connect");
    }

    public void call(String message) {
        System.out.println("call and message: " + message);
    }

    // call when service end
    public void disconnect() {
        System.out.println("close");
    }

    public void init(){
        connect();
        call("init message");
    }

    public void close() {
        disconnect();
    }

    /*@Override
    public void afterPropertiesSet() throws Exception {
        // 의존관계 주입 이후 initial 하는 부분을 여기에 넣음
        connect();
        call("init message");
    }

    @Override
    public void destroy() throws Exception {
        // 빈이 종료될 때 호출
        // 위의 after과 이 메소드들은 스프링 전용 인터페이스이므로 스프링에 매우 의존적이라는 단점이 있다.
        disconnect();
    }*/
}
