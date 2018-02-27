import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

/**
 * PracticeActiom Create on 2018/2/27
 * Description:
 * 课本实践
 * author justin
 * version 1.0
 * Copyright (c) 2018/2/27 by justin
 */

public class PracticeActiom {

    static Trader raoul = new Trader("Raoul", "Cambridge");
    static Trader mario = new Trader("Mario", "Milan");
    static Trader alan = new Trader("Alan", "Cambridge");
    static Trader brian = new Trader("Brian", "Cambridge");
    static List<Transaction> transactions = Arrays.asList(
            new Transaction(brian, 2011, 300),
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950)
    );


    public static void main(String[] args) {

        //01    找出2011年的所有交易并按交易额排序(从低到高)
        List<Transaction> tr2011 = transactions.stream()
                .filter(time -> time.getYear() == 2011)
                .sorted(comparing(Transaction::getValue))
                .collect(Collectors.toList());
        System.out.println("tr2011 = " + tr2011);
        /*
        transactions.stream()
                .map(Transaction::getYear)  !! 错误： map是当要将一个流转为另一个流的时候使用的，这个不需要使用map！！！要清楚什么时候用map
                .filter(time -> time.equals(2011))
                .sorted(comparing(Transaction::getValue)) //因为用了map，所以我们这里流中无法获取getValue值了！
                .collect(Collectors.toList());
        */

        //02    交易员都在哪些不同的城市工作过
        List<String> cities = transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList()); // ⭐️ 或者使用：.collect(toSet());去掉distinct()
        System.out.println("cities = " + cities);

        //03    查找所有来自于剑桥的交易员，并按姓名排序
        List<Trader> traders = transactions.stream()
                .map(Transaction::getTrader)
                .filter(transaction -> transaction.getCity().equals("Cambridge"))
                .distinct()
                .sorted(comparing(Trader::getName))
                .collect(Collectors.toList());
        System.out.println("traders = " + traders);

        //...
        //08    找到交易额最小的交易
        Optional<Transaction> smallestTransaction = transactions.stream()
                .min(comparing(Transaction::getValue));
        System.out.println("smallestTransaction = " + smallestTransaction);
    }

}


class Trader {
    private final String name;
    private final String city;

    public Trader(String n, String c) {
        this.name = n;
        this.city = c;
    }

    public String getName() {
        return this.name;
    }

    public String getCity() {
        return this.city;
    }

    public String toString() {
        return "Trader:" + this.name + " in " + this.city;
    }
}

class Transaction {
    private final Trader trader;
    private final int year;
    private final int value;

    public Transaction(Trader trader, int year, int value) {
        this.trader = trader;
        this.year = year;
        this.value = value;
    }

    public Trader getTrader() {
        return this.trader;
    }

    public int getYear() {
        return this.year;
    }

    public int getValue() {
        return this.value;
    }

    public String toString() {
        return "{" + this.trader + ", " +
                "year: " + this.year + ", " +
                "value:" + this.value + "}";
    }
}