package com.example.core.scan.filter;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME) // 어노테이션 한번 찾아보기
@Documented
public @interface MyIncludeComponent {
}
