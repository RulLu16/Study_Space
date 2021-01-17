package com.example.core.discount;

import com.example.core.annotation.MainDiscountPolicy;
import com.example.core.member.Grade;
import com.example.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
//@Qualifier("mainDiscountPolicy")
@MainDiscountPolicy // 새로 만든 어노테이션 적용
@Primary // 빈 조회시 우선순위를 줘서 이 빈을 먼저 조회 및 주입.
public class RateDiscountPolicy implements DiscountPolicy{

    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP){
            return price * discountPercent / 100;
        }

        return 0;
    }
}
