package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Repository.impl.MemoryMemberRepository;
import hello.core.member.Service.MemberService;
import hello.core.member.Service.impl.MemberServiceImpl;
import hello.core.order.Service.OrderService;
import hello.core.order.Service.impl.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService()    {
//        return new MemberServiceImpl(new MemoryMemberRepository());
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService()  {
        // No.1
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(
                memberRepository(),
                discountPolicy());
    }

    @Bean
    public MemoryMemberRepository memberRepository() {
        // No.2? No.3?
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy()  {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
