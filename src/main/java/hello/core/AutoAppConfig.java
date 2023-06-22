package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration                //기존 예제로 @Configuration 사용했 던 클래스가 있음으로 @Configuration 클래스들을 빈으로 등록 안되게 처리
@ComponentScan(
//        basePackages = "hello.core.member" 특정 패키지에서 찾을지 지정 가능
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = Configuration.class))
public class AutoAppConfig {


}
