package com.lkjed.lambda;

import org.junit.Test;

import java.util.Comparator;
import java.util.function.Consumer;

public class LambdaDemo01 {
    @Test
    public void test01(){
       //常规写法
       Runnable run1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("学习Java8");
            }
        };
       run1.run();
        System.out.println("==========================");
        //Lambda表达式写法
        Runnable run2 = ()-> System.out.println("学习Java8 之Lambda表达式");
        run2.run();
    }
    @Test
    public void test02(){
        //常规写法
        Comparator<Integer> comparator1 = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1,o2);
            }
        };
        int compare1 = comparator1.compare(123, 3123);
        System.out.println(compare1);
        System.out.println("==========================");
        //Lambda表达式写法
        Comparator<Integer> comparator2 = (o1,o2) -> Integer.compare(o1,o2);
        int compare2 = comparator2.compare(123, 33);
        System.out.println(compare2);
        System.out.println("==========================");
        //方法引用
        Comparator<Integer> comparator3 = Integer::compare;
        System.out.println(comparator3.compare(123, 31));
    }
    @Test
    public void test3(){
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        consumer.accept("谎言和誓言的区别是什么？");
        System.out.println("=============================================");
        Consumer<String> consumer1 = (s) -> {
            System.out.println(s);
        };
        consumer1.accept("谎言和誓言的区别是什么？");

    }
    @Test
    public void test04(){
        //Lambda表达式写法
        Comparator<Integer> comparator2 = (o1, o2) -> o1.compareTo(o2);
        System.out.println(comparator2.compare(4521,3125456));
        System.out.println(Math.round(-1.5));
    }

}
