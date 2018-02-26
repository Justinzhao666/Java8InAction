package top.zhaohaoren.aroundMode.good_demo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * GoodProcesssFile Create on 2018/2/6
 * Description:   
 *     针对环绕执行模式 进行接口抽离改进：以适应行为多变性以及适应于lambda表达式
 * author justin     
 * version 1.0 
 * Copyright (c) 2018/2/6 by justin   
 */ 
 
public class GoodProcessFile {

    private static String processFile(FileProcesser fp) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
            return fp.process(br);
        }
    }

    public static void main(String[] args) throws IOException {

        String oneline = processFile((BufferedReader br) -> br.readLine()+" ");
        String twoline = processFile((BufferedReader br) -> br.readLine()+br.readLine());
        //.......
    }
}
