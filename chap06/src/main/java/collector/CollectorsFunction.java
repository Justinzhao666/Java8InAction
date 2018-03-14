package collector;

import top.zhaohaoren.streamDemo.model.Dish;

import java.util.*;
import java.util.stream.Stream;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;

/**
 * collector.CollectorsFunction Create on 2018/3/14
 * Description:
 *      Collectors工具类常用的方法
 * author justin
 * version 1.0
 * Copyright (c) 2018/3/14 by justin
 */

public class CollectorsFunction {

    public static void main(String[] args) {

        Stream<Dish> menuStream = Stream.empty();

        //toList 流转为list
        List<Dish> dishes = menuStream.collect(toList());

        //toSet  将流转为set
        Set<Dish> dishes2 = menuStream.collect(toSet());

        //counting 统计流个数
        long howManyDishes = menuStream.collect(counting());

        //summingInt 流中int数值进行求和
        int totalCalories = menuStream.collect(summingInt(Dish::getCalories));

        //averagingInt 流中数值取平均值
        double avgCalories = menuStream.collect(averagingInt(Dish::getCalories));

        //同时获得最大最小平均值等信息
        IntSummaryStatistics menuStatistics = menuStream.collect(summarizingInt(Dish::getCalories));

        //joining 字符串进行拼接，调用对象的toString方法
        String shortMenu = menuStream.map(Dish::getName).collect(joining(", "));

        //maxBy 最大的元素的Optional包装
        Optional<Dish> fattest = menuStream.collect(maxBy(comparingInt(Dish::getCalories)));

        //minBy 最小的元素的Optional包装
        Optional<Dish> lightest = menuStream.collect(minBy(comparingInt(Dish::getCalories)));

        //reducing 归约为单个值
        int totalCalories2 = menuStream.collect(reducing(0, Dish::getCalories, Integer::sum));

        //collectingAndThen 搜集后的结果需要转换类型
        int howManyDishes2 = menuStream.collect(collectingAndThen(toList(), List::size));

        //groupingBy 分组
        Map<Dish.Type, List<Dish>> dishesByType = menuStream.collect(groupingBy(Dish::getType));

        //partitioningBy 分区
        Map<Boolean, List<Dish>> vegetarianDishes = menuStream.collect(partitioningBy(Dish::isVegetarian));


    }
}
