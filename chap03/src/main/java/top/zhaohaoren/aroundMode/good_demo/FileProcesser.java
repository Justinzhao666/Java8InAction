package top.zhaohaoren.aroundMode.good_demo;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * FileProcesser Create on 2018/2/6
 * Description:   
 *     被抽离出来的行为
 * author justin     
 * version 1.0 
 * Copyright (c) 2018/2/6 by justin   
 */

@FunctionalInterface
public interface FileProcesser {
    String process(BufferedReader br) throws IOException;
}
