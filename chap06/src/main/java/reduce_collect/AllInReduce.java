package reduce_collect;

import top.zhaohaoren.streamDemo.model.Dish;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

/**
 * AllInReduce Create on 2018/3/13
 * Description:
 *      以上的这些例子其实都可以使用 reduce这个Collector中的方法来实现，因为他们都是属于归约操作：递归的将一系列数转为一个数！
 *      所有提供的现成的一些方法，其实都可以通过该reducing()归约操作来实现！
 * author justin
 * version 1.0
 * Copyright (c) 2018/3/13 by justin
 */

public class AllInReduce {

    public static List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH));

    private static void sum() {
        // stream的 方法和 collector接口方法有很多的重合，但是道理是一樣的
        /**
         *
         * reduce 三个参数的时候：
         * 1： 初始值，也就是当流为空的默认值
         * 2： 一个映射函数，将元素映射为归约需要的类型，可以称之为转换函数
         * 3： 一个函数，用于归约规则：一定是归约型的
         * */
        int total = menu.stream().collect(reducing(0, Dish::getCalories, (i, j) -> i + j));
        int total2 = menu.stream().collect(reducing(0, Dish::getCalories, Integer::sum));
    }

    private static void max() {
        Optional<Dish> max = menu.stream().collect(reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2));

        Stream<Integer> stream = Arrays.asList(1, 2, 3, 4, 5, 6).stream();

        // 这是stream提供的reduce方法，参数1：初始值，参数2：一个制造出另一个需要的归约参数方法，参数3：归约规则
        List<Integer> numbers = stream.reduce(
                new ArrayList<Integer>(),
                (List<Integer> l, Integer e) -> {
                    l.add(e);
                    return l;
                },
                (List<Integer> l1, List<Integer> l2) -> {
                    l1.addAll(l2);
                    return l1;
                });
        System.out.println("numbers = " + numbers);
    }

    public static void main(String[] args) {
        max();
    }
}
