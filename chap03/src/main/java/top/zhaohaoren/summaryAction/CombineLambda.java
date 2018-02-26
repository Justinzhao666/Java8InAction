package top.zhaohaoren.summaryAction;

import top.zhaohaoren.functionToParams.Apple;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.util.Comparator.comparing;

/**
 * CombineLambda Create on 2018/2/26
 * Description:
 *      使用lambda表达式可以方便的组合一些规则，主要就是熟悉：Comparator，Predicate，Function在java8中的建议用法!
 * author justin
 * version 1.0
 * Copyright (c) 2018/2/26 by justin
 */

public class CombineLambda {

    /**
     * 比价器复合。Comparator接口的复合
     */
    private static void ComparatorCombination() {
        List<Integer> list = new ArrayList<>();
        //以后对列表进行排序的时候都这么写： 链式链接比较规则 -> reversed使比较逆序，thenComparing为第二排序规则
        list.sort(comparing(Integer::intValue).reversed().thenComparing(Integer::doubleValue));
    }

    /**
     * 谓词复合: Predicate谓词接口提供的 negate(not),or,and 的拼接组合。 从左向右确定优先级
     */
    private static void PredicateCombination() {
        Predicate<Apple> redApple = (apple) -> apple.getColor().equals("red");
        Predicate<Apple> redAndHeavyApple = redApple.and(apple -> apple.getWeight() > 150);
        Predicate<Apple> redAndHeavyAppleOrGreen =redApple.and(apple -> apple.getWeight() > 150).or(a -> "green".equals(a.getColor()));
    }

    /**
     * 函数复合 Function接口的复合: andThen f(g(x)) 和 compose g(f(x))
     */
    private static void FunctionCombination(){
        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x + 1;
        Function<Integer, Integer> h = f.andThen(g); //g(f(x)) 先f和作用于g
        int result = h.apply(1);
        System.out.println(result);
    }

}
