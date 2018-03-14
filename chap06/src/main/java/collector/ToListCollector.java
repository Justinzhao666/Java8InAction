package collector;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * ToListCollector Create on 2018/3/14
 * Description:   
 *     自定义一个collector实现类，
 * author justin     
 * version 1.0 
 * Copyright (c) 2018/3/14 by justin   
 */ 
 
public class ToListCollector<T> implements Collector<T, List<T>, List<T>>
{

    /**
     * @return 需要返回一个方法，给前方调用。
     */
    @Override
    public Supplier<List<T>> supplier() {
        return ArrayList::new;
    }

    @Override
    public BiConsumer<List<T>, T> accumulator() {
        return null;
    }

    @Override
    public BinaryOperator<List<T>> combiner() {
        return null;
    }

    @Override
    public Function<List<T>, List<T>> finisher() {
        return null;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return null;
    }
}
