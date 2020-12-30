package com.example.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeTraceAop {
// 공통 로직을 원하는 메소드에 적용해 효율성을 올려줄 수 있다.
// 프록시라는 가짜 빈을 만들어 이를 실행하고 proceed를 하면 그 때 진짜 메소드를 실행
    @Around("execution(* com.example.spring..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long time = finish - start;
            System.out.println("time: " + joinPoint.toString() + " " + time + "ms");
        }
    }
}
