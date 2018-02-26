package top.zhaohaoren.functionToParams;

import com.alibaba.fastjson.JSON;
import java.util.Arrays;
import java.util.List;

/**
 * Usage Create on 2018/2/6
 * Description:   
 *     行为参数化案例
 * author justin     
 * version 1.0 
 * Copyright (c) 2018/2/6 by justin   
 */ 
 
public class Main{
    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(new Apple(80,"green"), new Apple(155, "green"),
                new Apple(120, "red"));

        /*lambda表达式使用*/
        List<Apple> greenApples = AppleUtils.filter(inventory,(Apple apple)-> apple.getColor().equals("green"));

        System.out.println(JSON.toJSONString(greenApples));
    }
}
