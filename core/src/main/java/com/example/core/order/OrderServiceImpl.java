package com.example.core.order;

import com.example.core.discount.DiscountPolicy;
import com.example.core.discount.FixDiscountPolicy;
import com.example.core.discount.RateDiscountPolicy;
import com.example.core.member.MemberRepository;
import com.example.core.member.MemoryMemberRepository;
import com.example.core.member.Member;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    // 변경하려면 서비스 코드를 고쳐야하는 문제 ->  OCP, DIP같은 객체지향 설계원칙 준수가 x
    //private DiscountPolicy discountPolicy;
    // 이처럼 아예 인터페이스에만 의존하게 해서 설계원칙을 준수 -> but, 구현체를 못 넣으니 에러.
    // 그러므로 누군가가 대신 만들어서 주입해주어야 함.

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
