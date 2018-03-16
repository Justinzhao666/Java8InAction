package collector;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import static java.util.stream.Collector.Characteristics.CONCURRENT;
import static java.util.stream.Collector.Characteristics.IDENTITY_FINISH;

/**
 * ToListCollector Create on 2018/3/14
 * Description:
 *     自定义一个collector实现类： 自定义收集器
 *     模仿Collectors.toList方法
 * author justin
 * version 1.0
 * Copyright (c) 2018/3/14 by justin
 */

public class ToListCollector<T> implements Collector<T, List<T>, List<T>> {

    /**
     * 步骤1：
     *
     * @return 需要返回一个方法，给前方调用。
     */
    @Override
    public Supplier<List<T>> supplier() {
        return ArrayList::new;
    }

    /**
     * 步骤2：
     *
     * @return 执行归约操作的函数：将前面的运算结果和当前的运算结果合并起来
     */
    @Override
    public BiConsumer<List<T>, T> accumulator() {
        return List::add;
    }

    /**
     * @return 用于流的并行处理，将并行处理的各个部分整合在一起
     * 流的并行处理，会将流递归的进行对分，每个自流处理完了，再递归的进行流的合并（如同逆步操作）
     */
    @Override
    public BinaryOperator<List<T>> combiner() {
        return (list1, list2) -> {
            list1.addAll(list2);
            return list1;
        };
    }

    /**
     * 步骤3：
     *
     * @return 将累加器的结果转为需要的值
     * 这些数据其实都在流中！并不属于这些类，这些类只是一些工具方法。
     */
    @Override
    public Function<List<T>, List<T>> finisher() {
        return Function.identity(); //直接返回累加结果，不做其他处理
    }

    /**
     * 返回一个不可变的集合，以定义收集器的行为(是否进行流的并行归约，优化提示等)
     *
     * @return
     */
    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(IDENTITY_FINISH, CONCURRENT));
    }

//    enum Characteristics {
//        CONCURRENT,   accumulator()可以多线程调用，如果这个被标记了，但是UNORDERED没有被标记，只有数据源是无序(set...)的时候才允许并行归约 【所以只有无序流才可以被并行归约】
//        UNORDERED,    归约结果不受流遍历和累积顺序影响
//        IDENTITY_FINISH   累加器的返回结果将直接作为该搜集器的返回结果。
//    }

}

/**
 *
 *
 *     public static <T>  Collector<T, ?, List<T>> toList() {
                         return new CollectorImpl<>((Supplier<List<T>>) ArrayList::new, List::add,
                         (left, right) -> { left.addAll(right); return left; },
                         CH_ID);}
 *
 */
