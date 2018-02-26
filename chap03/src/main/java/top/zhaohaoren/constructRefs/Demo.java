package top.zhaohaoren.constructRefs;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Demo Create on 2018/2/26
 * Description:   
 *     
 * author justin     
 * version 1.0 
 * Copyright (c) 2018/2/26 by justin   
 */ 
 
public class Demo {

    public static void main(String[] args) {
//        不带参数  -- jdk8 提供了Supplier 函数式接口来接受0个参数的lambda表达式
//        Supplier<Apple> c1 = Apple::new;
//        Apple apple1  = c1.get();

//        包含1参数 -- 1.8 系统提供Function
        Function<Integer,Apple> c1 = Apple::new;
        c1.apply(10);

//        包含2参数 -- 1.8 系统提供BiFunction
        BiFunction<Integer,String,Apple> c2 = Apple::new;
        c2.apply(10,"hah");

//        包含3参数 -- 需要我们自己定义该TriFunction接口来接受该lambda传递
        TriFunction<Integer,String,String,Apple> c3 = Apple::new;
        c3.apply(10,"apple","red");
    }
}

/**
 * 自定义的函数式接口
 * @param <T> 参数1
 * @param <U> 参数2
 * @param <V> 参数3
 * @param <R> 返回值
 */
@FunctionalInterface
interface TriFunction<T, U, V, R>{
    R apply(T t, U u, V v);
}

class Apple{
    Integer weight;
    String name;
    String color;
    public Apple(Integer weight) {
        this.weight = weight;
    }

    public Apple(Integer weight, String name) {
        this.weight = weight;
        this.name = name;
    }

    public Apple(Integer weight, String name, String color) {
        this.weight = weight;
        this.name = name;
        this.color = color;
    }
}
