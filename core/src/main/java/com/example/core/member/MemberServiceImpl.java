package com.example.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component // 이 annotation을 붙여서 componentscan이 자동으로 bean 등록을 할 수 있게 함
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Autowired // 자동으로 의존관계를 주입해주는 annotation. componentscan 하나만으로는 주입이 힘들기 때문
    public MemberServiceImpl(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {

        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {

        return memberRepository.findById(memberId);
    }

    //for test
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
