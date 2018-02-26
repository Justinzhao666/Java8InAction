package top.zhaohaoren.streamDemo.model;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * StreamExecuteOrder Create on 2018/2/26
 * Description:   
 *     流的中间操作的执行过程
 * author justin     
 * version 1.0 
 * Copyright (c) 2018/2/26 by justin   
 */ 
 
public class StreamExecuteOrder {
    public static List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH) );

    public static void main(String[] args) {
        //流的中间操作前后关系依赖性是在编译的时候就可以确定的
        List<String> names = menu.stream()
                .filter(d -> {
                    System.out.println("filtering:" + d.getName());
                    return d.getCalories() > 300;
                })
                .map(d -> {
                    System.out.println("mapping:" + d.getName());
                    return d.getName();
                })
                .limit(3)
                .collect(toList());
        System.out.println(names);
    }

    /*  out::  流中一个个取出数据执行一遍所有中间操作
        filtering:pork
        mapping:pork
        filtering:beef
        mapping:beef
        filtering:chicken
        mapping:chicken
        [pork, beef, chicken]
    */
}
