package reduce_collect;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.reducing;

/**
 * Demos Create on 2018/3/13
 * Description:
 *      使用流对集合的一些数据统计工作
 *          总结：
 *              1. 对于流的一些操作，从两个地方入手： stream类自带，collectors类提供 --两者可能有重复功能
 *              2. 涉及到基本数据类型的，我们最好将流转为基本数据流来进行归约计算，如：mapToInt()
 * author justin
 * version 1.0
 * Copyright (c) 2018/3/13 by justin
 */

public class DataStatisticsDemo {

    public static void main(String[] args) {
        new DataStatisticsDemo().count_max_min_ave();
    }


    /**
     * 对流计数
     */
    private void count() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7);

        long count = (long) list.size(); // 这个不提
        long count1 = list.stream().count(); //直接使用流的方法
        long count2 = list.stream().collect(Collectors.counting()); //使用collectors工具类提供的方法
        long count3 = list.stream().collect(reducing(0L, e -> 1L, (i, j) -> i + j)); //同样使用Collectors提供的方法，但是reduce具有一般性，使用reduce可以应用多种归约需求！
    }

    /**
     * 最大最小值
     */
    private void getMaxAndMin() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        Optional<Integer> max = list.stream().max(Comparator.comparingInt(Integer::intValue));
        // TODO: 2018/3/13 我还模糊着的：这些接口的传递，怎样让自己清晰的知道自己传递什么样的接口 -> lambda其实也就是一个方法体，一个方法参数是一个接口，那么就是需要传入一个和接口定义相同的方法体给接口。
        // TODO: 2018/3/13 当然这些接口应该只有一个实现，你传入一个方法体，就相当于实现了该接口，在这些具有方法参数的方法如collect中，会内部将传入的方法引用到数据中
        Optional<Integer> max2 = list.stream().collect(Collectors.maxBy(Comparator.comparingInt(Integer::intValue)));
    }

    /**
     * 求和
     */
    private void sum() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        int total = list.stream().mapToInt(Integer::intValue).sum(); //推荐使用IntStream来操作
        int total2 = list.stream().collect(Collectors.summingInt(Integer::intValue));
    }

    /**
     * 平均数
     */
    private void average() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        OptionalDouble average = list.stream().mapToInt(Integer::intValue).average();
        double average2 = list.stream().collect(Collectors.averagingInt(Integer::intValue));
    }


    /**
     * 一个包装类，可以一次性取出 总计，最大最小值等数据
     */
    private void count_max_min_ave() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        IntSummaryStatistics dataStatics = list.stream().collect(Collectors.summarizingInt(Integer::intValue));
        long count = dataStatics.getCount();
        int max = dataStatics.getMax();
        int min = dataStatics.getMin();
        long sum = dataStatics.getSum();
        double ave = dataStatics.getAverage();
        System.out.println("dataStatics = " + dataStatics);
    }


}
