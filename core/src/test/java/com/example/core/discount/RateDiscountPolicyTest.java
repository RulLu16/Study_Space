package com.example.core.discount;

import com.example.core.member.Grade;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.example.core.member.Member;

import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {

    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("vip는 10% 할인 적용")
    void vip_o(){
        Member member = new Member(1L, "memberVip", Grade.VIP);

        int discount = discountPolicy.discount(member, 20000);

        Assertions.assertThat(discount).isEqualTo(2000);
    }

    @Test
    @DisplayName("vip 이외는 할인 적용 x")
    void vip_x(){
        Member member = new Member(1L, "memberBasic", Grade.BASIC);

        int discount = discountPolicy.discount(member, 20000);

        Assertions.assertThat(discount).isEqualTo(0);
    }
}