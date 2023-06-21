package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {


    @Test //******** 공유 필드는 조심해야 한다 , 스프링 빈은 항상 무상태로 설계하자 *******
    void statefulServiceSingleton(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean("statefulService", StatefulService.class);
        StatefulService statefulService2 = ac.getBean("statefulService", StatefulService.class);

          //ThreadA : A 사용자가 10000 주문 (ex 상태설계)
//        statefulService1.order("userA",10000);
          //ThreadB : B 사용자가 20000 주문(ex 상태설계)
//        statefulService2.order("userB",20000);

          //ThreadA 사용자 주문 금액 조회
//        int price = statefulService1.getPrice();

          //인스턴스가 같기 때문에 서비스1,2 는 상관이 없다 (10000에서 20000 변경)
//        System.out.println("price = " + price); // -> 20000 출력


        int userA = statefulService1.order("userA", 10000);
        statefulService2.order("userB",20000);

        //무상태 설계를 함으로서 10000 출력
        System.out.println("statefulService1 = " + userA);

//        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);

    }



    static class TestConfig{

        @Bean
        public StatefulService statefulService(){
            return new StatefulService();

        }

    }

}