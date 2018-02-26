package top.zhaohaoren.functionToParams;

/**
 * Apple Create on 2018/2/6
 * Description:
 *      苹果实体类
 * author justin
 * version 1.0
 * Copyright (c) 2018/2/6 by justin
 */

public class Apple {
    String color;
    int weight;

    public Apple(int weight, String color) {
        this.color = color;
        this.weight = weight;
    }

    public Apple() {
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
