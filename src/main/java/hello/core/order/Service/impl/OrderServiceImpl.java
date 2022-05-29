package hello.core.order.Service.impl;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.Repository.MemberRepository;
import hello.core.member.Repository.impl.MemoryMemberRepository;
import hello.core.order.Order;
import hello.core.order.Service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

//    private final MemberRepository memberRepository = new MemoryMemberRepository();

    //    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    /*
    @Autowired
    public OrderServiceImpl(MemoryMemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
    //*/
    @Autowired
    public OrderServiceImpl(MemoryMemberRepository memberRepository,
                            @MainDiscountPolicy DiscountPolicy rateDiscountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = rateDiscountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // Test 용도
    public MemberRepository getMemberRepository()   {
        return memberRepository;
    }

    // 수정자 자동 주입
    @Autowired
    public DiscountPolicy setDiscountPolicy (@MainDiscountPolicy DiscountPolicy discountPolicy) {
        return discountPolicy;
    }
}
