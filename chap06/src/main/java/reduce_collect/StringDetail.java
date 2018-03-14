package reduce_collect;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * StringDetail Create on 2018/3/13
 * Description:   
 *     流可以对序列中有字符串的，进行拼接，也是归约操作，由collectors提供
 * author justin     
 * version 1.0 
 * Copyright (c) 2018/3/13 by justin   
 */ 
 
public class StringDetail {

    public static void main(String[] args) {
        new StringDetail().joinString();
        new StringDetail().joinStringWithChar();
    }

    private void joinString(){
        List<String> strings = Arrays.asList("i ","love ","you");
        String str = strings.stream().collect(Collectors.joining());
        System.out.println("str = " + str);
    }

    private void joinStringWithChar(){
        List<String> strings = Arrays.asList("i ","love ","you");
        String str = strings.stream().collect(Collectors.joining("💕"));
        System.out.println("str = " + str);
    }
}
