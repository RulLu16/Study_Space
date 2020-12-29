package com.example.spring.controller;

import com.example.spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    } // bean 등록된 것들에서 가져와서 주입
    // 생성자 주입이 처음 만들때 주입 후 수정 불가능하므로 가장 많이 쓰이는 방법
}
