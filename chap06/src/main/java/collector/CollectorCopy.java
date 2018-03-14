package collector;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * CollectorCopy Create on 2018/3/14
 * Description:
 * <p>
 * author justin
 * version 1.0
 * Copyright (c) 2018/3/14 by justin
 */
public interface CollectorCopy<A, T, R> {

    /**
     * 前面四个都是返回的方法
     */
    Supplier<A> supplier();

    BiConsumer<A, T> accumulator();

    BinaryOperator<A> combiner();

    Function<A, R> finisher();

    /**
     *
     */
    Set<Collector.Characteristics> characteristics();


    enum Characteristics {
        CONCURRENT,
        UNORDERED,
        IDENTITY_FINISH
    }
}
