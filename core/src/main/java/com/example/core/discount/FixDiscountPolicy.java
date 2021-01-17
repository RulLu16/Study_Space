package com.example.core.discount;

import com.example.core.member.Grade;
import com.example.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component // rate랑 둘다 component로 scan하므로 타입에서 중복이 생김 => autowired일 경우 조회시 중복 발생
@Qualifier("fixDiscountPolicy") // 빈 이름바꾸기가 아니라, 주입 및 조회시 생각할 추가 정보를 제공
public class FixDiscountPolicy implements DiscountPolicy{

    private int discountFixAmount = 1000;

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP){
            return discountFixAmount;
        }

        return 0;
    }
}
