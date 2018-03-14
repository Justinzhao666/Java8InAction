package group;

import reduce_collect.AllInReduce;
import top.zhaohaoren.streamDemo.model.Dish;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

/**
 * GroupDemo Create on 2018/3/14
 * Description:
 *      将流中的元素安装给定的分组规则进行分组
 *          使用collectors的groupby方法：
 *              使用一个参数：
 *                      传入分组规则并返回key，一个确定返回Map中key的函数
 *              使用两个参数：
 *                      参数1：确定Map中key的函数
 *                      参数2：将继续作用于分组后 各组的value list中（可以继续分组，也可以是其他函数，只要能作用这list）--最后新流的操作
 *
 * author justin
 * version 1.0
 * Copyright (c) 2018/3/14 by justin
 */

public class GroupDemo {
    public static void main(String[] args) {

        basicGroup();
        multilevelGroup();
        statisticsGroup();
    }

    public enum CaloricLevel {DIET, NORMAL, FAT}

    /**
     * 单级分组
     * groupingBy(传入分组规则方法，返回值未分组的key，和分完组对应的list)
     */
    private static void basicGroup() {
        Map<CaloricLevel, List<Dish>> dishGroup = AllInReduce.menu.stream()
                .collect(groupingBy(
                        GroupDemo::getCaloricLevel
                ));
        System.out.println("dishGroup = " + dishGroup);
    }


    /**
     * 多级分组
     * groupingBy(参数1：第一级分组的key，参数2：子级的链表的分组规则)
     * 依次类推可以分组分多级
     * 返回的值，有几级就会有几级的map
     */
    private static void multilevelGroup() {
        Map<Dish.Type, Map<CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel = AllInReduce.menu.stream()
                .collect(groupingBy(
                        Dish::getType,
                        groupingBy(
                                GroupDemo::getCaloricLevel
                        ))
                );
        System.out.println("dishesByTypeCaloricLevel = " + dishesByTypeCaloricLevel);
    }


    /**
     * 对分组后的数据进行统计
     */
    private static void statisticsGroup(){

        Map<Dish.Type,Long> typesCount = AllInReduce.menu.stream()
                .collect(groupingBy(
                        Dish::getType, //参数1： 分组的key
                        counting() // 作用于分组后每一组中的list
                ));

        Map<Dish.Type, Dish> mostCaloricByType =  AllInReduce.menu.stream()
                .collect(groupingBy(
                        Dish::getType,
                        collectingAndThen( //需要将collect返回的元素，转为其他类型，使用该放啊
                                maxBy(Comparator.comparingInt(Dish::getCalories)),
                                Optional::get   //返回的Optional<Dish> 此例转为Dish，当然也可以转为其他的类型
                                )
                ));
        System.out.println("mostCaloricByType = " + mostCaloricByType);
        System.out.println("typesCount = " + typesCount);
    }


    /**
     * 分组规则
     * @param dish 需要被分组的对象
     * @return 分组的key
     */
    private static CaloricLevel getCaloricLevel(Dish dish) {
        if (dish.getCalories() <= 400) return CaloricLevel.DIET;
        else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
        else return CaloricLevel.FAT;
    }



}


