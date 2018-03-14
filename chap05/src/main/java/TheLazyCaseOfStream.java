import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * TheLazyCaseOfStream Create on 2018/3/8
 * Description:
 * 直接遇到截断流的时候，流中的那些操作才会真正被执行。否则stream会一直挂在那
 * author justin
 * version 1.0
 * Copyright (c) 2018/3/8 by justin
 */

public class TheLazyCaseOfStream {

    /**
     * 下面如果map后面不加collect的话，list1依然会为空，不会执行map中的add操作
     * <p>
     * 就是因为stream的惰性，只有遇到阶段流的时候，这段语句才会去执行， 不然只是一个流状态。
     */
    public static void main(String[] args) {
        final List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 6);
        List<Integer> list1 = new ArrayList<>();
        list.stream().map(l -> list1.add(l));
//        list.stream().map(l ->list1.add(l)).collect(Collectors.toList());
//        list.forEach(l->list1.add(l));
        System.out.println("list1 = " + list1);

    }
}
