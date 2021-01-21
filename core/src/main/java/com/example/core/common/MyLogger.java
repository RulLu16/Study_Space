package com.example.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
@Scope(value = "request")
// 에러 발생. 왜? => request scope는 실제 요청이 들어 와야 생성됨. 그 이전에 주입을 시도하면 생성 안된 빈이므로 에러가 발생
// 그럼 요청 들어오고 빈이 만들어질 때 주입을 하자 => provider의 사용이 대두됨
public class MyLogger {

    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message){
        System.out.println("[" + uuid + "][" + requestURL + "] " + message);
    }

    @PostConstruct
    public void init(){
        this.uuid = UUID.randomUUID().toString();
        System.out.println("request start");
    }

    @PreDestroy
    public void close(){
        System.out.println("close");
    }
}
