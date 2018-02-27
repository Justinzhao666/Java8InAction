import top.zhaohaoren.streamDemo.model.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * NumberStream Create on 2018/2/27
 * Description:
 * 数值流:
 *  在reduce进行求和操作的时候会暗藏装箱成本，需要转为基本类型进行求和运算。但是因为需求对Stream不具有普遍性，所以没有添加接口在上面
 *  Java 8引入了三个原始类型特化流接口：IntStream、DoubleStream和 LongStream，分别将流中的元素特化为int、long和double，从而避免了暗含的装箱成本。
 *
 *  IntStream、DoubleStream和 LongStream 这些就是数值流！
 *
 *  通过mapToInt() 等等等将普通流转换为数值流！
 *
 * author justin
 * version 1.0
 * Copyright (c) 2018/2/27 by justin
 */

public class NumberStream {

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

    public static void main(String[] args) {

    }

    /**
     *  使用Stream提供的mapToInt接口会返回IntStream，该IntStream接口包含sum接口可以直接求和，并且没有拆箱损耗。
     *  流空 返回0
     *  IntStream还支持其他的方便方法，如max、min、average等。
     */
    private static void intStreamUsage() {
        // 使用流
        int calories = menu.stream()
                .mapToInt(Dish::getCalories)
                .sum();

        // 反包装流
        IntStream intStream = menu.stream().mapToInt(Dish::getCalories);
        //        将 Stream 转 换为数值流
        Stream<Integer> stream = intStream.boxed();
        //        将数值流转 换为Stream

        // 数值流返回值可能为空
        OptionalInt maxCalories = menu.stream()
                .mapToInt(Dish::getCalories)
                .max();
        int max = maxCalories.orElse(1);

        // 数值流范围
        IntStream evenNumbers = IntStream.rangeClosed(1, 100) .filter(n -> n % 2 == 0); //取出1-100之间的偶数，rangeClosed取出1-100之间所有的数字
        System.out.println(evenNumbers.count());
    }
}
