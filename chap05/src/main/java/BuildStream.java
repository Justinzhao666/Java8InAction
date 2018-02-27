import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.IntSupplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * BuildStream Create on 2018/2/27
 * Description:
 * 构建流：
 * 如何从值序列、数组、文件来创建流，甚至由生成函数来创建无限流!
 * author justin
 * version 1.0
 * Copyright (c) 2018/2/27 by justin
 */

public class BuildStream {


    /**
     * 由值生成流： Stream.of
     */
    private static void ValueToStream() {
        Stream<String> stream = Stream.of("Java 8 ", "Lambdas ", "In ", "Action");
        Stream<String> emptyStream = Stream.empty(); //空流
        stream.map(String::toUpperCase).forEach(System.out::println);
    }

    /**
     * 由数组生成流：  Arrays.stream
     */
    public static void ArrayToStream() {
        int[] numbers = {2, 3, 5, 7, 11, 13};
        int sum = Arrays.stream(numbers).sum();
    }

    /**
     * 由文件生成流：  Files.lines，打开的文件流结束会自动关闭
     */
    public static void FileToStream() {
        long uniqueWords = 0; //该文件中有多少个不同的值
        try (Stream<String> lines =
                     Files.lines(Paths.get("data.txt"), Charset.defaultCharset())) {
            uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" "))) //将每一行生成流，个体为单词，再整合为一个流
                    .distinct() //去重
                    .count();   //计数
        } catch (IOException e) {
        }
    }

    /**
     * 由函数生成流：  可以创建无限流
     * Stream API提供了两个静态方法来从函数生成流:  Stream.iterate  和   Stream.generate。
     */
    public static void FunctionToStream() {

        //使用iterator
        Stream.iterate(0, n -> n + 2)
                .limit(10)
                .forEach(System.out::println);

        //斐波纳契数列
        Stream.iterate(new int[]{0,1},num -> new int[]{num[1],num[0]+num[1]})
                .limit(20)
                .map(num->num[0])
                .forEach(System.out::println);

        //使用generate
        //generate不是依次 对每个新生成的值应用函数的,需要一个Supplier<T>类型的Lambda提供新的值。 值不是流计算出来的，而是lambda提供的。
        Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);

        //斐波纳契数列使用generate，不适用于并行
        IntSupplier fib = new IntSupplier(){ //需要一个提供数据源的函数
            private int previous = 0;
            private int current = 1;
            public int getAsInt(){
                int oldPrevious = this.previous;
                int nextValue = this.previous + this.current;
                this.previous = this.current;
                this.current = nextValue;
                return oldPrevious;
            } };
        IntStream.generate(fib).limit(10).forEach(System.out::println);
    }
}
