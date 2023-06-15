package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {

    public static void main(String[] args) {
//            AppConfig appConfig = new AppConfig();
        //Config 안에 Bean 객체들                                                        //config Class
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

                                                                    //메서드명와 맞춰야함    반환타입
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
//        MemberService memberService = appConfig.memberService();
//        MemberService memberService = new MemberServiceImpl(null);


        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);
//        OrderService orderService = appConfig.orderService();
//        OrderService orderService = new OrderServiceImpl(null,null);
        
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);//vip 회원 생성
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);

        System.out.println("order = " + order);
        System.out.println("orderDiscount = " + order.calculatePrice());


    }
}
