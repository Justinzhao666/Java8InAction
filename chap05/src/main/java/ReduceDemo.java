import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * ReduceDemo Create on 2018/2/27
 * Description:
 *      归约: 把一个流中的元素组合起来,得到一个值。
 *      什么时候归约？ 当处理list的时候，你只需要一个结果并且是需要整个list合作生成的，这时候要往归约上面想想。
 * author justin
 * version 1.0
 * Copyright (c) 2018/2/27 by justin
 */

public class ReduceDemo {

    public static void main(String[] args) {
        getSum();
    }


    /**
     * reduce:
     * 两个参数：
     * 1 初始值
     * 2 归约规则
     * 还有一种没有初始值的方法，返回Optional值，因为返回可能为空（归约列表无值）
     */
    private static void getSum() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
//        int sum = numbers.stream().reduce(0, (a, b) -> a + b); // 0为初始值，0开始和numbers里面的数字进行归约。
        int sum = numbers.stream().reduce(0, Integer::sum); // 0为初始值，0开始和numbers里面的数字进行归约。
        System.out.println(sum);

        //求最大最小值
//        Optional<Integer> max = numbers.stream().reduce((x, y) -> (x > y) ? x : y);
        Optional<Integer> max = numbers.stream().reduce(Integer::max);
        System.out.println(max);
    }


}
