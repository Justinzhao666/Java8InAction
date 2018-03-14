package partition;

import reduce_collect.AllInReduce;
import top.zhaohaoren.streamDemo.model.Dish;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.partitioningBy;

/**
 * partitionDemo Create on 2018/3/14
 * Description:
 *      应用的思路：
 *          一组数据需要分两拨的时候（比如，分开质数和非质数）
 *
 *      流的分区：用于分区的函数只返回一个boolean值，所以只能分为true 和 false两组
 *      相对于分组： 个人觉得-> 分组是多分类的情况，分区是双分类的特例
 *      用法：
 *          partitioningBy(param1)
 *              分组的规则，返回值为boolean
 *          partitioningBy(param1,param2)
 *              分组的规则
 *              分完组后，新的list的处理逻辑，决定着返回值Map的value值
 * author justin
 * version 1.0
 * Copyright (c) 2018/3/14 by justin
 */

public class partitionDemo {
    public static void main(String[] args) {
        partition();
    }

    private static void partition() {
        Map<Boolean, List<Dish>> partitionDish = AllInReduce.menu.stream()
                .collect(partitioningBy(
                        Dish::isVegetarian
                ));
        System.out.println("partitionDish = " + partitionDish);
    }


    /**
     * 将流分为质数和非质数
     * @param n
     * @return
     */
    public Map<Boolean, List<Integer>> partitionPrimes(int n) {
        return IntStream.rangeClosed(2, n).boxed() //box 就是装箱的意思
                .collect(
                        partitioningBy(candidate -> isPrime(candidate)));
    }

    /**
     * 判断是否为质数
     *
     * @param candidate 所判断的数
     * @return
     */
    private static boolean isPrime(int candidate) {
//        return IntStream.range(2, candidate).noneMatch(i -> candidate % i == 0);
        int candidateRoot = (int) Math.sqrt((double) candidate); //优化：只需要验证<=平方根因子的数
        return IntStream.range(2, candidate).noneMatch(i -> candidate % i == 0);
    }
}
