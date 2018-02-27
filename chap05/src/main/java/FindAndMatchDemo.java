import top.zhaohaoren.streamDemo.model.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * FindAndMatch Create on 2018/2/27
 * Description:
 *      查找和匹配在Stream的使用
 * author justin
 * version 1.0
 * Copyright (c) 2018/2/27 by justin
 */

public class FindAndMatchDemo {

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

    /*  匹配   */

    /**
     * 存在一个匹配 anyMatch
     * 类似：
     * allMatch 流中全部匹配该谓词
     * noneMatch 流中全部不匹配
     * <p>
     * 结果是一个boolean 是截断流
     */
    public void anyMatchDemo() {
        if (menu.stream().anyMatch(Dish::isVegetarian)) {
            System.out.println("The menu is (somewhat) vegetarian friendly!!");
        }
    }


    /*  查找   */

    /**
     * findAny：返回流中任意元素
     * 加上filter可以找出满足条件的一个元素，并且利用了短路性质，找到后立刻结束不会继续遍历下面元素。
     * 类似：
     * findFirst： 找出流中第一个匹配谓词项目（流不会并行！）
     */
    public void findAnyDemo() {
        Optional<Dish> dish = menu.stream()
                .filter(Dish::isVegetarian)
                .findAny();
        dish.ifPresent(d -> System.out.println(d.getName()));
    }

}
