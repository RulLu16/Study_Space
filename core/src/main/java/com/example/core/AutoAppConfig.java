package com.example.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        //basePackages = "com.example.core.member" // 스캔할 시작 위치를 지정할 수 있음. 여러개 가능
        // 지정하지 않으면, componentScan이 있는 클래스의 위치가 시작 위치가 됨.
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
        // 이미 있던 AppConfig와 충돌을 피하기 위해 임의로 exclude
)
public class AutoAppConfig {
}
