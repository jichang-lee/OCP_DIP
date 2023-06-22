package hello.core.scan.filter;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.context.annotation.ComponentScan.*;

public class ComponentFilterAppConfigTest {


    @Test
    void filterScan(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentAppConfig.class);

        BeanA beanA = ac.getBean(BeanA.class);
        assertThat(beanA).isNotNull();

        //exclude 로 필터처리 하여 테스트로 예외 처리 확인 (Assert.junit)
        assertThrows(NoSuchBeanDefinitionException.class,
                ()-> ac.getBean(BeanB.class));
    }




    @ComponentScan(                 //필터타입 애노테이션은 디폴트 생략가능
            includeFilters = @Filter(type = FilterType.ANNOTATION,classes = MyIncludeComponent.class),
            excludeFilters = @Filter( MyExcludeComponent.class))

    static class ComponentAppConfig{

    }

}
