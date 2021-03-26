package com.example.demo.vo;

import com.example.demo.entity.GoogleUser;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    // 인증된 사용자 정보
    // entity는 직렬화가 없는 것이 좋으므로 여기에 따로 구현.
    private String name;
    private String email;
    private String picture;

    public SessionUser(GoogleUser user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
