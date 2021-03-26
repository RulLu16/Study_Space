package com.example.demo.config.auth;

import com.example.demo.entity.Role;
import com.example.demo.service.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity // security 설정 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .authorizeRequests() // url별 권환 관리 설정 옵션 시작
                .antMatchers("/", "/css/**", "/images/**", // 권한 관리 대사 지정
                        "/js/**", "/h2-console/**").permitAll()
                .antMatchers("/api/v1/**").hasRole(Role.
                USER.name()) // user 권한 있는 사람만 접근 가능
                .anyRequest().authenticated() // 나머지는 인증된 사용자만 접근 가능
                .and()
                .logout()
                .logoutSuccessUrl("/") // 로그아웃 설정
                .and()
                .oauth2Login()
                .userInfoEndpoint()
                .userService(customOAuth2UserService);
    }
}
