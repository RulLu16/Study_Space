package com.example.demo.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    // 사용자의 권한 관리 enum

    GUEST("ROLE_GUEST", "guest"),
    USER("ROLE_USER", "일반 사용자");

    private final String key;
    private final String title;
}
