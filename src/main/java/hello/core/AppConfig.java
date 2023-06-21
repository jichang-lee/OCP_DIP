package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //없어도 @Bean 등록이 가능 하지만 싱글톤은 보장되지 않는다 , 어떠한 설정을할 때 그냥 고민하지말고 쓰자
public class AppConfig {

        //@Bean memberService -> memberRepository -> new MemoryMemberRepository
        //@Bean orderService -> memberRepository,discountPolicy
        //위 빈 객체에서 memberRepository(new MemoryMemberRepository) 를 두 번 호출한다. 싱글톤에 부적합?
        //스프링은 (컨테이너) 스프링 빈이 싱글톤이 되도록 보장해준다.

        //기대값
        //call memberService
        //call memberRepository
        //call memberRepository
        //call orderService
        //call memberRepository

        //실제값
        //call memberService
        //call memberRepository
        //call orderService

        //TestCode -> test.singleton.ConfigurationSingletonTest


        @Bean
        public MemberService memberService(){
            System.out.println("call AppConfig.memberService");
            return new MemberServiceImpl(memberRepository());
        }
        @Bean
        public MemberRepository memberRepository() {
            System.out.println("call AppConfig.memberRepository");
            return new MemoryMemberRepository();
        }

        @Bean
        public OrderService orderService(){
            System.out.println("call AppConfig.orderService");
            return new OrderServiceImpl(memberRepository(), discountPolicy());
        }
        @Bean
        public DiscountPolicy discountPolicy(){
            return new RateDiscountPolicy();
        }




}
