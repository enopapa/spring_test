package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class StatefuleServiceTest {

    @Test
    void statefuleServiceSingleton()    {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        StatefulService statefulService1 = ac.getBean("statefulService", StatefulService.class);
        StatefulService statefulService2 = ac.getBean("statefulService", StatefulService.class);

        // ThreadA : A User 10000원 주문
        statefulService1.order("userA", 10000);
        // ThreadB : B User 20000원 주문
        statefulService2.order("userB", 20000);

        // ThreadA : User A 주문 금액 조회
        int price = statefulService1.getPrice();
        // ThreadA : User A 는 10000원을 기대했지만, 기대와는 다르게 20000원 출력
        System.out.println("price = " + price);

        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    @Test
    void statefuleServiceSingleton2()    {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        StatefulService statefulService1 = ac.getBean("statefulService", StatefulService.class);
        StatefulService statefulService2 = ac.getBean("statefulService", StatefulService.class);

        // ThreadA : A User 10000원 주문
        int userAPrice = statefulService1.orderEdit("userA", 10000);
        // ThreadB : B User 20000원 주문
        int userBPrice = statefulService2.orderEdit("userB", 20000);

        // ThreadA : User A 주문 금액 조회
//        int price = statefulService1.getPrice();
        // ThreadA : User A 는 10000원을 기대했지만, 기대와는 다르게 20000원 출력
        System.out.println("userAPrice = " + userAPrice);
        System.out.println("userBPrice = " + userBPrice);

        Assertions.assertThat(userAPrice).isEqualTo(10000);
        Assertions.assertThat(userBPrice).isEqualTo(20000);
    }

    static class TestConfig {

        @Bean
        public StatefulService statefulService()    {
            return new StatefulService();
        }
    }
}
