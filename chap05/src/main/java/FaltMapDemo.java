import com.alibaba.fastjson.JSON;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * faltMapDemo Create on 2018/2/27
 * Description:
 *      使用流的flatMap接口：
 *          map 将一个流中的每个个体作用lambda函数，得到一个新的流，新的流里面的个体是新的数据
 *          flatMap 作用于源流中每一个个体，然后lambda一般会将这些个体转为流对象，最后flatMap将这若干个流整合为一个整体的流。
 * author justin
 * version 1.0
 * Copyright (c) 2018/2/27 by justin
 */

public class FaltMapDemo {

    public static void main(String[] args) {
        String[] arrs = {"Goodbye", "World"};
        Stream<String> streamArrs = Arrays.stream(arrs); //Arrays.stream将一个数组转为流对象。


        List<String> list = streamArrs
                .map(word -> word.split(""))
                //需要先用map将所数据全部拆开，以让flatMap转为一个个流
                .flatMap(Arrays::stream)
                //flatMap 依次将map后的新流中元素执行lambda：Arrays::stream方法将流转为stream（这样就是一个stream[stream]流了），再由flatMap转为一个整体的流：（扁平化）
                .distinct()
                //去重
                .collect(Collectors.toList());

        System.out.println(list);
        demo1();
    }


    /**
     * 将两个数组转换为数对
     * [1, 2, 3]，[3, 4]  ---->  [(1, 3), (1, 4), (2, 3), (2, 4), (3, 3), (3, 4)]
     * 并过滤数对和为3的倍数的元素。
     */
    private static void demo1() {
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);

        List<int[]> pairs = numbers1.stream() //源流 （1，2，3）
                .flatMap(i -> numbers2.stream()  // step1: 依次调用源流的数据作用于lambda //step3: 将生成的多个流合并为1个流
                        .filter(j -> (i + j) % 3 == 0) //过滤结果和为3倍数的
                        .map(j -> new int[]{i, j}) // step2: numbers2的map生成新的流，个体为[i,j],这样就生成了多个流
                ).collect(Collectors.toList());
        System.out.println(JSON.toJSONString(pairs));
    }
}
