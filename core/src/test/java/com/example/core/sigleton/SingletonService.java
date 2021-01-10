package com.example.core.sigleton;

public class SingletonService {
    // singleton을 한번 만들어보기

    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance(){
        return instance;
    }

    // 생성자를 private으로 선언하여 외부에서 객체 생성되는 것을 막음
    private SingletonService(){
    }

    public void logic(){
        System.out.println("singleton call");
    }
}
