package com.lkjed.lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class LambdaDemo02 {
    @Test
    public void test01(){
        List<String> list = Arrays.asList("北京","南京","东京","田径");
        List<String> filterList = filterString(list, new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.contains("京");
            }
        });
        System.out.println(filterList);
        System.out.println("=======================");
        List<String> filterList1 = filterString(list, s -> s.contains("京"));
        System.out.println(filterList1);
    }
    public List<String> filterString(List<String> list, Predicate<String> pre){
        ArrayList<String> filterList = new ArrayList();
        for (String s: list){
            if (pre.test(s)){
                filterList.add(s);
            }
        }
        return filterList;
    }
}
