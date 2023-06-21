package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextBasicTest {


    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);


    @Test
    @DisplayName("빈 이름 조회")
    void basicFindName(){
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        System.out.println("memberService = " + memberService);
        System.out.println("memberService class = " + memberService.getClass());
        assertThat(memberService).isInstanceOf(memberService.getClass());

    }

    @Test
    @DisplayName("빈 이름없이 타입으로 조회")
    void basicFindType(){
        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(memberService.getClass());
    }


    @Test
    @DisplayName("구체 타입으로 조회")
    void basicFindName2(){
        MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class);
        assertThat(memberService).isInstanceOf(memberService.getClass());

    }



    @Test
    @DisplayName("빈 이름으로 테스트 실패(조회X)")
    void notFindBean(){
//    NoSuchBeanDefinitionException
        assertThrows(NoSuchBeanDefinitionException.class ,
                () -> ac.getBean("test",MemberService.class));

    }

}
