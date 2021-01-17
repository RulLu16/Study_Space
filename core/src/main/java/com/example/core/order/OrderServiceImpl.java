package com.example.core.order;

import com.example.core.annotation.MainDiscountPolicy;
import com.example.core.discount.DiscountPolicy;
import com.example.core.discount.FixDiscountPolicy;
import com.example.core.discount.RateDiscountPolicy;
import com.example.core.member.MemberRepository;
import com.example.core.member.MemoryMemberRepository;
import com.example.core.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("orderService") // 빈 이름 지정도 가능
//@RequiredArgsConstructor // final 붙은 필수 값에 대한 생성자를 만들어줌 -lombok
public class OrderServiceImpl implements OrderService{

    // @Autowired // 필드 자동 의존주입. 타입으로 조회하기 때문에 중복이면 문제발생할 수 있음
    // 외부에서 변경이 불가능해 테스트가 힘들고, DI 프레임워크가 없으면 아무것도 못함 => 사용 하지 말자..
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;
    // 변경하려면 서비스 코드를 고쳐야하는 문제 ->  OCP, DIP같은 객체지향 설계원칙 준수가 x
    //private DiscountPolicy discountPolicy;
    // 이처럼 아예 인터페이스에만 의존하게 해서 설계원칙을 준수 -> but, 구현체를 못 넣으니 에러.
    // 그러므로 누군가가 대신 만들어서 주입해주어야 함.

    /*@Autowired // 생성자가 하나면 생략해도 스프링 빈 사용시 자동 주입해줌.
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy rateDiscountPolicy){
        this.memberRepository = memberRepository;
        this.discountPolicy = rateDiscountPolicy; // 중복 시 필드 명 또는 파라미터 이름으로 다시 매칭
    }*/

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, /*@Qualifier("mainDiscountPolicy")*/
                            @MainDiscountPolicy DiscountPolicy discountPolicy){
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy; //qualifier를 사용해 해당 이름의 빈을 찾아서 넣음.
        // 직접 만든 어노테이션으로 적용 가능
    }

    /*@Autowired // setter를 통해 의존 자동 주입
    public void setMemberRepository(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }*/

    /*@Autowired // 일반 메서드에 의존 자동 주입. 거의 사용하지 않음.
    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy){
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }*/

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //for test
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
