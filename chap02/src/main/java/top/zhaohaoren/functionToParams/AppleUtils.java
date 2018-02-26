package top.zhaohaoren.functionToParams;

import java.util.ArrayList;
import java.util.List;

/**
 * Apples Create on 2018/2/6
 * Description:   
 *     处理苹果列表的工具类
 * author justin     
 * version 1.0 
 * Copyright (c) 2018/2/6 by justin   
 */ 
 
public class AppleUtils<T> {
    public static <T> List<T> filter(List<T> list,Predicate<T> predicate){
        List<T> result = new ArrayList<>();
        for(T e: list){
            if(predicate.test(e)){
                result.add(e);
            } }
        return result;
    }
}
