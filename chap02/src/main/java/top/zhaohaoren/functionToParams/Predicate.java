package top.zhaohaoren.functionToParams;

/**
 * top.zhaohaoren.functionToParams.Predicate Create on 2018/2/6
 * Description:
 *      过滤条件接口，测试lambda表达式
 * author justin
 * version 1.0
 * Copyright (c) 2018/2/6 by justin
 */
@FunctionalInterface
public interface Predicate<T> {
    boolean test(T t);
}
