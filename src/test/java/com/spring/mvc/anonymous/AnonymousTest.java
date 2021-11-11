package com.spring.mvc.anonymous;

import org.junit.jupiter.api.Test;

public class AnonymousTest {

    @Test
    void test() {
        Car sonata = new Sonata();
        sonata.run();

        //익명 클래스 (일회용 재활용은 안됨)
        Car ferrari = new Car() {

            @Override
            public void run() {
                System.out.println("페라리가 달립니다.");
            }
        };
        ferrari.run();


        Dealer pororo = new Dealer();
        pororo.getCar(sonata);
        pororo.getCar(new Car() {
            @Override
            public void run() {
                System.out.println("페라리가 달립니다.");
            }
        });

    }

    @Test
    void test2() {
        Calculator addCal = new Calculator() {
            @Override
            public double operate(int n1, int n2) {
                return n1 + n2;
            }
        };

        Calculator mulitCal = new Calculator() {
            @Override
            public double operate(int n1, int n2) {
                return n1 * n2;
            }
        };

        Calculator addCal2 = (n1, n2) -> {
            return n1 + n2 ;
        };
        Calculator addCal3 = (n1, n2) -> n1 + n2 ;

        Calculator multiCal2 = (n1, n2) -> n1 * n2;

        System.out.println(addCal2.operate(100, 100));
        System.out.println(addCal3.operate(100, 150));
        System.out.println(addCal.operate(100, 200));
        System.out.println(mulitCal.operate(200, 200));
        System.out.println(multiCal2.operate(200, 200));
    }

}
