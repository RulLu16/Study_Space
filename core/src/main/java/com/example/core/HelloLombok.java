package com.example.core;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter // lombok을 통해 getter setter를 어노테이션 processing으로 자동으로 설정되게 함
public class HelloLombok {

    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setAge(12);

        int age = helloLombok.getAge();
    }
}
