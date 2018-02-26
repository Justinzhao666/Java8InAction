package top.zhaohaoren.aroundMode.bad_demo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Main Create on 2018/2/6
 * Description:   
 *      br.readLine(); 这段代码是业务多变的部分，但是读取文件和释放文件这些就是公用不变的部分。
 *      这就是典型的环绕执行模式： 环绕 br.readLine();
 *      当业务修改的时候需要些很多重复的环绕部分代码 改进——> 行为参数化！
 * author justin     
 * version 1.0 
 * Copyright (c) 2018/2/6 by justin   
 */ 
 
public class BadProcesssFile {
    public static String processFile() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
            return br.readLine();
        }
    }
}
