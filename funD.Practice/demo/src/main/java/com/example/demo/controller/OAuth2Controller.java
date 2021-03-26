package com.example.demo.controller;

import com.example.demo.vo.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class OAuth2Controller {

    private final HttpSession httpSession;

    @GetMapping("/login-prac")
    @ResponseBody
    public SessionUser index(Model model) {

        SessionUser user = (SessionUser) httpSession.getAttribute("googleUser");

        return user; // 동작 확인용. 정보 다 들어가있으니 이렇게 반환하면 보안문제 발생
    }
}
